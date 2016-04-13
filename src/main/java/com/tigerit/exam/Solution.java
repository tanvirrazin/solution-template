package com.tigerit.exam;


import static com.tigerit.exam.IO.*;

/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    @Override
    public void run() {
        // your application entry point

        // sample input process
        //String string = readLine();

        //Integer integer = readLineAsInteger();

        // sample output process
        //printLine(string);
        //printLine(integer);

        Integer cases, casesIter, nTable, nTableIter, nRowIter, nColumnIter, nQuery, nQueryIter, queryLineIter;
        Integer inputMatrix[][][] = new Integer[11][101][101];

        int FROMTableIndex, JOINTableIndex;

        String tableNames[] = new String [11];
        String tableNamesForSql[] = new String[11];
        String columnNames[][] = new String [11][101];

        String queryLines[][] = new String[51][4];

        String SELECTColumnName[], FROMTableName, JOINTableName, tmpTableName[];
        String conditions[][] = new String[2][2];

        Integer cAndr[] = new Integer[2];
        Integer nColumn[] = new Integer[11];
        Integer nRow[] = new Integer[11];

        cases = readLineAsInteger();
        casesIter = 0;

        while(casesIter < cases) {

            // Taking table input
            nTable = readLineAsInteger();
            nTableIter = 0;
            while(nTableIter < nTable) {

                // reading table name
                tableNames[nTableIter] = readStrings()[0];

                // reading column-number and row-number for respective table
                cAndr = readInts();
                nColumn[nTableIter] = cAndr[0];
                nRow[nTableIter] = cAndr[1];

                // Reading column-names
                columnNames[nTableIter] = readStrings();

                // Reading table-data
                nRowIter = 0;
                while(nRowIter < nRow[nTableIter]) {
                    inputMatrix[nTableIter][nRowIter] = readInts();
                    nRowIter++;
                }

                nTableIter++;
            }

            // Taking Query input
            nQuery = readLineAsInteger();
            nQueryIter = 0;
            while(nQueryIter < nQuery) {
                tableNamesForSql = tableNames.clone();
                queryLineIter = 0;
                while(queryLineIter < 4) {
                    queryLines[nQueryIter][queryLineIter] = readLine();
                    queryLineIter++;
                }

                SELECTColumnName = getColumns(queryLines[nQueryIter][0]);

                tmpTableName = getTable(queryLines[nQueryIter][1]);
                if(tmpTableName.length > 1) {
                    int inde = getStringListIndexOf(tableNamesForSql, nTable, tmpTableName[0]);
                    if(inde > -1) {
                        tableNamesForSql[inde] = tmpTableName[1];
                    }
                    FROMTableName = tmpTableName[1];
                } else {
                    FROMTableName = tmpTableName[0];
                }


                tmpTableName = getTable(queryLines[nQueryIter][2]);
                if(tmpTableName.length > 1) {
                    int inde = getStringListIndexOf(tableNamesForSql, nTable, tmpTableName[0]);
                    if(inde > -1) {
                        tableNamesForSql[inde] = tmpTableName[1];
                    }
                    JOINTableName = tmpTableName[1];
                } else {
                    JOINTableName = tmpTableName[0];
                }

                conditions = getConditions(queryLines[nQueryIter][3]);


                FROMTableIndex = getStringListIndexOf(tableNamesForSql, nTable, FROMTableName);
                JOINTableIndex = getStringListIndexOf(tableNamesForSql, nTable, JOINTableName);

                for(int i=0; i < nRow[FROMTableIndex]; i++) {
                    for(int j=0; j < nRow[JOINTableIndex]; j++) {
                        for(int k=0; k < nColumn[FROMTableIndex]; k++) {
                            System.out.print(inputMatrix[i][j][k] + " ");
                        }
                        for(int k=0; k < nColumn[JOINTableIndex]; k++) {
                            System.out.print(inputMatrix[i][j][k] + " ");
                        }
                        printLine(" ");
                    }
                    printLine(" ");
                }

                readLine(); // skipping blank line
                nQueryIter++;
            }






            // Data displaying
//            nTableIter = 0;
//            while(nTableIter < nTable) {
//                printLine(tableNames[nTableIter]);
//                printLine(nColumn[nTableIter]);
//                printLine(nRow[nTableIter]);
//
//                nColumnIter = 0;
//                String printingColumnNames = "";
//                while(nColumnIter < nColumn[nTableIter]) {
//                    printingColumnNames = printingColumnNames + columnNames[nTableIter][nColumnIter] + " ";
//                    nColumnIter++;
//                }
//                printLine(printingColumnNames);
//
//                nRowIter = 0;
//                String printingRecords = "";
//                while(nRowIter < nRow[nTableIter]) {
//                    printingRecords = "";
//                    nColumnIter = 0;
//                    while(nColumnIter < nColumn[nTableIter]) {
//                        printingRecords += Float.toString(inputMatrix[nTableIter][nRowIter][nColumnIter]) + " ";
//                        nColumnIter++;
//                    }
//                    printLine(printingRecords);
//                    nRowIter++;
//                }
//
//                nTableIter++;
//            }
//            printLine(nQuery);
//
//            nQueryIter = 0;
//            while(nQueryIter < nQuery) {
//                printLine(queryLines[nQueryIter][0] + " " +
//                          queryLines[nQueryIter][1] + " " +
//                          queryLines[nQueryIter][2] + " " +
//                          queryLines[nQueryIter][3]);
//                nQueryIter++;
//            }
            // Data displaying




            casesIter++;
        }
    }

    public static String[] getColumns(String input) {
        input = input.substring(7);
        if(input.charAt(0) == '*') {
            return new String[0];
        }
        String[] inputParts = input.split(", ");
        return inputParts;
    }

    public static String[] getTable(String input) {
        input = input.substring(5);
        return input.split(" ");

    }

    public static String[][] getConditions(String input) {
        input = input.substring(3);
        String parts[] = new String[2];
        String conditions[][] = new String[2][2];

        parts = input.split(" = ");

        conditions[0] = parts[0].split("\\.");
        conditions[1] = parts[1].split("\\.");

        return conditions;
    }

    public static int getStringListIndexOf(String[] list, Integer nT, String elem) {
        for (int i = 0; i < nT; i++) {
            if (list[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
