public class Gene {

    private char transformation;
    private String name;
    private boolean enable;


	public Gene(char transformation, String name) {
        this.transformation = transformation;
        this.name = name;
        int random = (int) Math.round( Math.random() );
        this.enable=(random==0)?false:true;
        
	}

    public char getTransformation() {
        return this.transformation;
    }

    public void setTransformation(char transformation) {
        this.transformation = transformation;
    }

    public String getName() {
       return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

    

    
}
