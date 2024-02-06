
public class Chromosome {
	   private Gene[] genes;
	    private double  fitValues;
	    private boolean fit;

	    public Chromosome(Gene[] genes, double fitvalues){
	        this.genes = genes;
	        this.fitValues=fitValues;
	    }

	    public Gene[] getGenes(){
	        return genes;
	    }
	    public void setGenes(Gene[] genes){
	        this.genes = genes;
	    }
	    

	    public int recalculateFitness(){
	        return 0;
	    }
	    
	    public String genesS(){
	        String chromosome="{";
	        for (int index = 0; index < genes.length; index++) {
	             chromosome+=genes[index].getTransformation()+",";
	        }
	        chromosome+="}";
	        
	        return chromosome;
	    }

	  //public void initializeChromosome(int size){
	  //    
	  //	Gene I=new Gene('I', "Induction");
	  //    Gene P=new Gene('P', "Privatization");
	  //    Gene A=new Gene('A', "Parallelization");
	  //    Gene R=new Gene('R', "Reduction");
	  //    Gene L=new Gene('L', "Loop-interchange");
	  //    Gene G=new Gene('G', "RangeAnalysis");
	  //    
	  //    Gene[] genes = new Gene[size];
	  //    genes[0]=I;
	  //    genes[1]=P;
	  //    genes[2]=A;
	  //    genes[3]=R;
	  //    genes[4]=L;
	  //    genes[5]=G;
	  //
	  //   this.setGenes(genes); 
	  //       
	  // }
	  //    


	    /**
	     * @return int return the fitValues
	     */
	    public double getFitValues() {
	        return fitValues;
	    }

	    /**
	     * @param fitValues the fitValues to set
	     */
	    public void setFitValues(double fitValues) {
	        this.fitValues = fitValues;
	    }

	    /**
	     * @return boolean return the fit
	     */
	    public boolean isFit() {
	        return fit;
	    }

	    public void setFit(Boolean fit) {
	        this.fit = fit;
	    }

}
