
public class Technique {
	
	public static final String INLINING_EXPANTION="mode=1:depth:0:pragma=0:foronly=1:complement=0:functions=";
	
	private String name;
	private String value;
	private String loopTinline;
	
	public Technique(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public Technique(String name, String value, String loopTinline) {
		super();
		this.name = name;
		this.value = value;
		this.loopTinline = loopTinline;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		String technique ="";
		if(this.name.equals("loop_interchange")&&this.getValue()=="0" || this.name.equals("tinline")&&this.getValue()=="0")
		{
			technique="";
		}
		else if(this.name.equals("loop_interchange")&&this.getValue()=="1")
		{
			technique= name;
		}
		else if(this.name.equals("tinline")&&this.getValue()=="1")
		{
			technique= name+ "=" + INLINING_EXPANTION+this.loopTinline;
		}
		else {
			technique=  name + "=" + value;
		}
		
		return technique;
			
		//return this.name.equals("loop_interchange")&&this.getValue()=="0"?"": this.name.equals("loop_interchange")&&this.getValue()=="1"?"-"+ name: "-" + name + "=" + value+" ";
	}
	
}