<?php
/*
Plugin Name: SchokoDB Plugin
Author: Kanyildiz Muhammedhizir
Version: 3.0
Description: SchokoDB Plugin
*/

//	Diese Funktion zeigt die aktuellen Schnaeppchen an

function schokoSchnaeppchen_func() {
	global $wpdb;
	ob_start();
	$res = $wpdb->get_results('SELECT bezeichnung, gewicht, "Kunstwerk" as ist FROM Produkt NATURAL JOIN Kunstwerk WHERE schaetzwert<2000 union SELECT bezeichnung, gewicht, "Standard" as ist FROM Produkt NATURAL JOIN Standardsortiment WHERE preis < 3;	');
		echo '<table class="table table-striped table-hover">';
		?>
		<th>
		Bezeichnung
		</th>
		<th>
		Gewicht
		</th>
		<th>
		Ist
		</th>
		<?php 
		foreach ($res as $r) {
			?>
			<tr>
			<td>	
				<?php echo $r->bezeichnung; ?>
			</td>
			<td class="small">
				<?php echo $r->gewicht; ?>g
			</td>
			<td>
				<?php echo $r->ist; ?>
			</td>
			</tr>

		<?php
	
	}
	echo '</table>';
	return ob_get_clean();
}

//	Diese Funktion liefert abhängig von der ausgewählten Kunstschau eine Auflistung der Kunstwerke und der freien Plätze erhalten

function schokoKunstschau_func() {
	global $wpdb;
	ob_start();
?>
	<form method="GET">
		<select name="kunstschau">
		<?php
		$kunstschau = $wpdb->get_results("select * from Kunstschau");
		foreach ($kunstschau as $k) {
			echo '<option value="'.$k->name.'">'.
				$k->name.
				'</option>';
		}
		?>
		</select>
		<input type="submit" value="Anzeigen" class="btn btn-default">
	</form>
<?php
        if (isset($_REQUEST['kunstschau'])) {
                $KunstschauDat = $wpdb->get_results(
		$wpdb->prepare('select bezeichnung, platz, name from Zeigt inner join Produkt on Zeigt.kunstwerknummer = Produkt.nummer order by name',
                $_REQUEST['kunstschau']));
		echo '<table class="table table-striped table-hover"><h1> '.$KunstschauDat[0]->name.'<h1>';
                echo '<tr><th><b>Kunstwerk</th> <th>Pl&auml;tze</th></tr>';
		foreach ($KunstschauDat as $r) {			
                        echo '<tr><td>'.$r->bezeichnung.'</td>';
                        echo '<td>'.$r->platz.'</td></tr>';
			}
		echo '</table>';
       		 }	
	return ob_get_clean();
}
add_shortcode('schokoSchnaeppchen','schokoSchnaeppchen_func');
add_shortcode('schokoKunstschau','schokoKunstschau_func');