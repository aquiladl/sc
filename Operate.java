import java.text.DecimalFormat;
import java.util.HashMap;


public class Operate {

	static HashMap<String,String> mapping = new HashMap<String,String>();
	static{
		mapping.put("miles", "mile");
		mapping.put("feet", "foot");	
		mapping.put("yards", "yard");
		mapping.put("inches", "inch");
		mapping.put("faths", "fath");
	}

	private Double result;

	public Double getResult() {
		return result;
	}

	public Operate(String type, Double value) {
		this.type = type;
		this.value = value;
	}

	private String type;
	private Double value;
	
	public Double getValue() {
		return value;
	}

	public void convert(HashMap<String,Double> rules){
		String tmpType = mapping.get(this.type);
		if (tmpType==null){
			tmpType = this.type;
		}
		new DecimalFormat("#.00");
		result = Double.parseDouble(new DecimalFormat("#.00").format(value*rules.get(tmpType)));
	}
}
