import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class ZDTest {
	
	public static void main(String[] args){
		HashMap<String,Double> rule = new HashMap<String,Double>();
		HashMap<String,Double> convert = new HashMap<String,Double>();
		HashMap<String,Double> operation = new HashMap<String,Double>();
		

		try{
			ZDTest test = new ZDTest();
			InputStream in = test.getClass().getResourceAsStream("input.txt");
			
			FileOutputStream fos=new FileOutputStream("c:\\output.txt");
			OutputStreamWriter osw=new OutputStreamWriter(fos);
			BufferedWriter bw=new BufferedWriter(osw);
			bw.write("jianghy_bj@hotmail.com");
			bw.newLine();
			bw.newLine();
			BufferedReader  reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = reader.readLine()) != null){
				if (line.length()<=0)
					continue;
				String[] def = line.split(" ");
				if (line.contains("=")){
					if (!rule.containsKey(def[1])){
						rule.put(def[1], Double.parseDouble(def[3]));
					}
				}else if ((!line.contains("="))&&!(line.contains("-")||(line.contains("+")))){
					convert.put(def[1], Double.parseDouble(def[0]));
					Operate opt = new Operate(def[1],Double.parseDouble(def[0]));
					opt.convert(rule);
					System.out.println(opt.getValue()+ " " + opt.getResult());	
					bw.write(opt.getResult() + " m" );
					bw.newLine();
				}else if (line.contains("-")||(line.contains("+"))){
					ArrayList<String> express = new ArrayList<String>();
					Double result = 0.0;
					String operator = null;
					for(int i=0;i<def.length;i++){
						if (((def[i].equals("-"))||(def[i].equals("+")))){
							Operate opt = new Operate(def[i-1],Double.parseDouble(def[i-2]));
							opt.convert(rule);
							express.add(opt.getResult().toString());
							if (operator==null){
								result = result + opt.getResult();	
								operator = def[i];
							}else{
								switch(operator){
									case "+": 
										result = result + opt.getResult();
										operator = def[i];
										break;  
									case "-":
										result = result - opt.getResult();
										operator = def[i];
										break; 
								}								
							}

							express.add(def[i]);
						}else if (i==def.length-1){
							Operate opt = new Operate(def[i],Double.parseDouble(def[i-1]));
							opt.convert(rule);
							express.add(opt.getResult().toString());
							switch(operator){
								case "+": 
									result = result + opt.getResult();
									operator = def[i];
									break;  
								case "-":
									result = result - opt.getResult();
									operator = def[i];
									break; 
							}
						}
					}
					bw.write(new DecimalFormat("#.00").format(result) + " m" );
					System.out.println(new DecimalFormat("#.00").format(result));
					bw.newLine();
				}
			}
			bw.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
