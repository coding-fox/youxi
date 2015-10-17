/**
 * 
 */
package org.lingxi.youxi.collect.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;
import org.lingxi.youxi.collect.beans.Outline;
import org.lingxi.youxi.collect.parser.TextParser;

/**
 * @author Administrator
 * 2015年9月20日
 */
public class TextParserTest_ {

	/**
	 * Test method for {@link org.lingxi.youxi.collect.parser.TextParser#saveData(java.util.List)}.
	 * @throws IOException 
	 */
	@Test
	public void testSaveData() throws IOException {
		
		TextParser parser = new TextParser();
		List<String> fetchContent = parser.fetchContent(readTxt2());
		System.out.println(fetchContent.get(0));
		Outline saveData = parser.parseOutline(fetchContent);
		System.out.println(saveData);
	}

	private String readTxt() throws IOException{
		FileInputStream fis = new FileInputStream("txt/十里平湖霜满天2015_09_19_20_16_24.txt");
		InputStreamReader read = new InputStreamReader(fis,"GBK");
		 BufferedReader bufferedReader = new BufferedReader(read);
         String lineTxt = null;
         StringBuffer buf = new StringBuffer();
         while((lineTxt = bufferedReader.readLine()) != null){
        	 buf.append(lineTxt)
        	 .append("-");
         }
         read.close();
         System.out.println(buf);
         return buf.toString();
         
	}
	private String readTxt2() throws IOException{
		
		File file = new File("txt/十里平湖霜满天2015_09_19_20_16_24.txt");
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(fis,"GBK");
	        int fileLen = (int)file.length();
	        char[] chars = new char[fileLen];
	        reader.read(chars);
	        String txt = String.valueOf(chars);
		System.out.println(txt);
		return txt;
		
	}
}
