package myservlet.control;

import java.util.Arrays;

public class Shiyan1_Bean{
   String []columnName ;           //�������
   String [][] tableRecord=null;   //��Ų�ѯ���ļ�¼
   public Shiyan1_Bean() {
      tableRecord = new String[1][1];
      columnName = new String[1]; 
   }
   public void setTableRecord(String [][] s){
      tableRecord=s;
   }
   public String [][] getTableRecord(){
      return tableRecord;
   }
   public void setColumnName(String [] s) {
      columnName = s;
   }
   public String [] getColumnName() {
      return columnName;
   }
@Override
public String toString() {
	return "Shiyan1_Bean [columnName=" + Arrays.toString(columnName) + ", tableRecord=" + Arrays.toString(tableRecord)
			+ "]";
}
   
   
}

