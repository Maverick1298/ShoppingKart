import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;

public class InputFileReader {
    int rows=0;
    int cols=0;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String xlFilePath="";
    HashMap<String,Integer>colNums=null;
    FileInputStream inputStream=null;
    FileOutputStream outputStream=null;
    public InputFileReader(String path){

        try{
            xlFilePath=path;
            inputStream=new FileInputStream(xlFilePath);
            System.out.println("File Successfully created");
            workbook=new XSSFWorkbook(inputStream);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setSheet(String sheetName){
        sheet=workbook.getSheet(sheetName);//this can take sheet name also as input
        rows=sheet.getLastRowNum();//total number of rows
        cols=sheet.getRow(0).getLastCellNum();//total number of columns
        populatedColumnNum();
    }
    //find no of cols and rows store them as HashMap<colname,colnumber>
    public void populatedColumnNum(){
        int colIndex=0;
        Row row=sheet.getRow(0);
        colNums=new HashMap<String,Integer>();
        Iterator<Cell> cells=row.cellIterator();

           while(cells.hasNext()){
               Cell cell=cells.next();
               colNums.put(cell.getStringCellValue(),colIndex);
               colIndex++;
           }
    }
    //returns the number of col using colname
    public int getColNumber(String colName){

        return colNums.get(colName);
    }
    public String getCellData(int rowNum,String colName){
        String cellValue;
        int colNum=getColNumber(colName);
        cellValue=getCellData(rowNum,colNum);
        return cellValue;
    }
    //get the value stored inside the cell
    public String getCellData(int rowNum,int colNum){
            String fetchedValue="";
            try{

                Row row=sheet.getRow(rowNum);
                fetchedValue=row.getCell(colNum).toString();
            }
        catch (Exception e){e.printStackTrace();}
        return fetchedValue;
    }
    //Wrtie data to file
    public void setCellData(int rowNum,String colName,String cellValue){

        XSSFRow row=null;
        XSSFCell cell=null;
        int colNum=-1;
        row=sheet.getRow(rowNum);
        colNum=getColNumber(colName);

        try{
            outputStream=new FileOutputStream(xlFilePath);
            cell = row.getCell(colNum);
            if(cell ==null) {
              cell=row.createCell(colNum);
              cell.setCellValue(cellValue);
              workbook.write(outputStream);//write data
              System.out.println(colNum+","+colName+","+row.getCell(colNum));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //close files once operation is complete
    public void closeFile(){
        try{
            inputStream.close();
            workbook.close();
        }
        catch (Exception e)
        {e.printStackTrace();}
    }
}
