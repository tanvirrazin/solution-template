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
        Integer matchingFromColumnIndex, matchingJoinColumnIndex;
        Integer inputMatrix[][][] = new Integer[11][101][101];
        boolean printCol;

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

            printLine("Test: " + (casesIter+1));

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

                matchingFromColumnIndex = getStringListIndexOf(columnNames[FROMTableIndex], nColumn[FROMTableIndex], conditions[0][1]);
                matchingJoinColumnIndex = getStringListIndexOf(columnNames[JOINTableIndex], nColumn[JOINTableIndex], conditions[1][1]);



                for(int k=0; k < nColumn[FROMTableIndex]; k++) {

                    if(SELECTColumnName.length == 0){
                        System.out.print(columnNames[FROMTableIndex][k] + " ");
                    } else {
                        for(int printI=0; printI < SELECTColumnName.length; printI++) {
                            if(SELECTColumnName[printI].equals(columnNames[FROMTableIndex][k])) {
                                System.out.print(columnNames[FROMTableIndex][k] + " ");
                                break;
                            }
                        }
                    }
                }
                for(int k=0; k < nColumn[JOINTableIndex]; k++) {
                    if(SELECTColumnName.length == 0){
                        System.out.print(columnNames[JOINTableIndex][k] + " ");
                    } else {
                        for(int printI=0; printI < SELECTColumnName.length; printI++) {
                            if(SELECTColumnName[printI].equals(columnNames[JOINTableIndex][k])) {
                                System.out.print(columnNames[JOINTableIndex][k] + " ");
                                break;
                            }
                        }
                    }
                }
                printLine(" ");

                for(int i=0; i < nRow[FROMTableIndex]; i++) {
                    for(int j=0; j < nRow[JOINTableIndex]; j++) {

                        if(inputMatrix[FROMTableIndex][i][matchingFromColumnIndex] == inputMatrix[JOINTableIndex][j][matchingJoinColumnIndex]) {
                            for(int k=0; k < nColumn[FROMTableIndex]; k++) {

                                if(SELECTColumnName.length == 0){
                                    System.out.print(inputMatrix[FROMTableIndex][i][k] + " ");
                                } else {
                                    for(int printI=0; printI < SELECTColumnName.length; printI++) {
                                        if(SELECTColumnName[printI].equals(columnNames[FROMTableIndex][k])) {
                                            System.out.print(inputMatrix[FROMTableIndex][i][k] + " ");
                                            break;
                                        }
                                    }
                                }
                            }
                            for(int k=0; k < nColumn[JOINTableIndex]; k++) {
                                if(SELECTColumnName.length == 0){
                                    System.out.print(inputMatrix[JOINTableIndex][j][k] + " ");
                                } else {
                                    for(int printI=0; printI < SELECTColumnName.length; printI++) {
                                        if(SELECTColumnName[printI].equals(columnNames[JOINTableIndex][k])) {
                                            System.out.print(inputMatrix[JOINTableIndex][j][k] + " ");
                                            break;
                                        }
                                    }
                                }
                            }
                            printLine(" ");
                        }
                    }
                }
                printLine(" ");

                readLine(); // skipping blank line
                nQueryIter++;
            }
            casesIter++;
        }
    }

    public static String[] getColumns(String input) {
        input = input.substring(7);
        if(input.charAt(0) == '*') {
            return new String[0];
        }
        String[] inputParts = input.split(", ");
        for(int i=0; i < inputParts.length; i++) {
            inputParts[i] = inputParts[i].split("\\.")[1];
        }
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
