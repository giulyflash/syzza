/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

public class DBUtil {

    //todo: mapear os tipos do Oracle
    public static Object getValueFromColumn(ResultSet resultset, int column) throws SQLException {
        ResultSetMetaData rsmd = resultset.getMetaData();
        switch (rsmd.getColumnType(column)) {
            case Types.SMALLINT: {
                return new Integer(resultset.getInt(column));
            }
            case Types.INTEGER: {
                return new Integer(resultset.getInt(column));
            }
            case Types.BIGINT: {
                return new Integer(resultset.getInt(column));
            }
            case Types.VARCHAR: {
                return resultset.getString(column);
            }
            case Types.DATE: {
                return resultset.getDate(column);
            }
            case Types.NUMERIC: {
                return new Double(resultset.getDouble(column));
            }
            default: {
                return resultset.getString(column);
            }
        }
    }
}
