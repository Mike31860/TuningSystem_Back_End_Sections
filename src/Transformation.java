
public class Transformation {
	
	private String name;
	private double value;
	
	
	public Transformation(String name, double value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return this.name.equals("loop-interchange")&&this.getValue()==0?"": this.name.equals("loop-interchange")&&this.getValue()==1?"-"+ name: "-" + name + "=" + value+" ";
	}
	
	
	

}
