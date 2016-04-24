<?php
/*
    Plugin Name: SchokoDB-Plugin
    Author: Kanyildiz Muhammedhizir
    Version: 3.0
    Description: Plugin SchokoDB
*/

/**
 * Funktion um alle Schnaeppchen auszugeben.
 *
 * Gibt alle Schnaeppchen (schaetzwert < 2000 oder preis < 3) der Schokofabrik aus.
 * @return Bezeichnung, Gewicht und Art aller Schnaeppchen in einer Tabelle.
 */
function schokoSchnaeppchen_func()
{
    $wpschokofabrik = new WPDB('user01', 'user01', 'Schokoladenfabrik', 'localhost');
    ob_start();

    $res = $wpschokofabrik->get_results("SELECT * FROM schnaeppchen");

    echo '<table class="table-striped table-hover"><tr><th>Bezeichnung</th><th>Gewicht in g</th><th>ist</th></tr>';
    foreach ($res as $r) {
        ?>
        <tr>
            <td>
                <?php echo $r->bezeichnung; ?>
            </td>
            <td class="small">
                <?php echo $r->gewicht; ?>
            </td>
            <td class="small">
                <?php echo $r->ist; ?>
            </td>
        </tr>
        <?php
    }
    echo '</table>';

    return ob_get_clean();
}

/**
 * Funktion um alle Kunstschauen und Details auszugeben.
 *
 * Gibt alle Kunstschauen der Schokofabrik in einem Formular zurueck. Waehlt man eine Kunstschau aus, erhaelt man eine
 * Tabelle aller ausgestellten Kunstwerke inklusive der Platzierung.
 * @return Kunstschauen und Details dazu in einem Formular bzw. in einer Tabelle.
 *
 */
function schokoKunst_func()
{
    $wpschokofabrik = new WPDB('user01', 'user01', 'Schokoladenfabrik', 'localhost');
    ob_start();
    ?>
    Bitte ausw&auml;hlen:
    <form method="GET">
        <select name="kunstschau">
            <?php
            $kunstschauen = $wpschokofabrik->get_results("SELECT * FROM kunstview");
            foreach ($kunstschauen as $k) {
                echo '<option value="' . $k->datum . ',' . $k->name . '">' . $k->datum . ': ' . $k->name . '</option>';
            }
            ?>
        </select>
        <input type="submit" value="Anzeigen" class="btn btn-default">
    </form>
    <?php
    
    if (isset($_REQUEST['kunstschau'])) {
        //aufteilen der durch , getrennten Primary Keys
        $requested = explode(',', $_REQUEST['kunstschau']);
        $kunstschaudetails = $wpschokofabrik->get_results($wpschokofabrik->prepare('SELECT bezeichnung, platz FROM platzierung JOIN prodview ON platzierung.kunstwerknummer=prodview.nummer WHERE name=%s AND datum=%s', $requested[1], $requested[0]));
        echo '<h4 class="well">Platzierungen f&uuml;r ' . $requested[1] . ': ' . $requested[0] . '</h4>';
        echo '<table class="table-striped table-hover"><tr><th>Bezeichnung</th><th>Platz</th></tr>';
        foreach ($kunstschaudetails as $r) {
            ?>
            <tr>
                <td>
                    <?php echo $r->bezeichnung; ?>
                </td>
                <td class="small">
                    <?php echo $r->platz; ?>
                </td>
            </tr>
            <?php
        }
        echo '</table>';
    }
    return ob_get_clean();
}

add_shortcode('schokoSchnaeppchen', 'schokoSchnaeppchen_func');
add_shortcode('schokoKunst', 'schokoKunst_func');