/****************************************************************************
 * **
 * ** Copyright (C) 2015 The Qt Company Ltd.
 * ** Contact: http://www.qt.io/licensing/
 * **
 * ** This file is part of the examples of the Qt Toolkit.
 * **
 * ** $QT_BEGIN_LICENSE:BSD$
 * ** You may use this file under the terms of the BSD license as follows:
 * **
 * ** "Redistribution and use in source and binary forms, with or without
 * ** modification, are permitted provided that the following conditions are
 * ** met:
 * **   * Redistributions of source code must retain the above copyright
 * **     notice, this list of conditions and the following disclaimer.
 * **   * Redistributions in binary form must reproduce the above copyright
 * **     notice, this list of conditions and the following disclaimer in
 * **     the documentation and/or other materials provided with the
 * **     distribution.
 * **   * Neither the name of The Qt Company Ltd nor the names of its
 * **     contributors may be used to endorse or promote products derived
 * **     from this software without specific prior written permission.
 * **
 * **
 * ** THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * ** "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * ** LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * ** A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * ** OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * ** SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * ** LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * ** DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * ** THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * ** (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * ** OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE."
 * **
 * ** $QT_END_LICENSE$
 * **
 * ****************************************************************************/

#include <QtWidgets>
#include <QtSql>

static bool createConnection()
{
        QSqlDatabase db = QSqlDatabase::addDatabase("QPSQL");
        db.setDatabaseName("schokofabrik");
        db.setUserName("schokofabrik");
        db.setPassword("schokofabrik");
        db.setHostName("vmdebian");
        if (!db.open()) {
                QMessageBox::critical(0, qApp->tr("Cannot open database"),
                                qApp->tr("Unable to establish a database connection.\n"
                                        "This example needs SQLite support. Please read "
                                        "the Qt SQL driver documentation for information how "
                                        "to build it.\n\n"
                                        "Click Cancel to exit."), QMessageBox::Cancel);
                return false;
        }

        return true;
}

void initializeModel(QSqlTableModel *model)
{
    model->setTable("person");
    model->setEditStrategy(QSqlTableModel::OnManualSubmit);
	model->select();

    model->setHeaderData(0, Qt::Horizontal, QObject::tr("ID"));
	model->setHeaderData(1, Qt::Horizontal, QObject::tr("First name"));
	model->setHeaderData(2, Qt::Horizontal, QObject::tr("Last name"));
}

QTableView *createView(QSqlTableModel *model, const QString &title = "")
{
	QTableView *view = new QTableView;
	view->setModel(model);
	view->setWindowTitle(title);
	return view;
}

int main(int argc, char *argv[])
{
	QApplication app(argc, argv);
	if (!createConnection())
		return 1;

	QSqlTableModel model;

	initializeModel(&model);

	QTableView *view1 = createView(&model, QObject::tr("Table Model (View 1)"));

	view1->show();

	return app.exec();
}
