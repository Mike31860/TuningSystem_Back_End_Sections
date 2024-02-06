import java.util.ArrayList;

public class Population {
	
	public final static Integer SIZE=6;
	private ArrayList<Chromosome> chromosomes;


	public Population(int length) {
		chromosomes = new ArrayList<Chromosome>(SIZE);
		 for (int index = 0; index < 10; index++) {
	        	Chromosome chromosome = null;
	        	boolean isChromosome = true;	
	        	while(isChromosome) {
	        		chromosome = initializeChromosome(SIZE);
	        		isChromosome= chromosomes.contains(chromosome);
	        		
	        	}
	        	chromosomes.add(chromosome);
	        }
		
	}
   
    public ArrayList<Chromosome> getChromosomes(){
        return chromosomes;
    }

    public void  setChromosomes(ArrayList<Chromosome> chromosomes){
        this.chromosomes=chromosomes;
    }

//    public Population initializePopulation(){
//        for (int index = 0; index < 10; index++) {
//        	Chromosome chromosome = null;
//        	boolean isChromosome = true;	
//        	while(isChromosome) {
//        		chromosome = initializeChromosome(SIZE);
//        		isChromosome= chromosomes.contains(chromosome);
//        		
//        	}
//        	chromosomes.add(chromosome);
//        }
//        return this;
//    }

    /* public void sortChromosomeByFitness(){
        Arrays.sort(chromosomes, (chromosome1, chromosome2)->{
            int flag=0;
            if(chromosome1.getFitvalues()>chromosome2.getFitvalues()) flag=-1;
            else if (chromosome1.getFitvalues()<chromosome2.getFitvalues()) flag=1;
            return flag;
        });       
    } */
    public String ToString(){
        String chromosome="";
        for (int i = 0; i < chromosomes.size(); i++) {          
            chromosome+="Chromosome "+i+" {";
            for (int j = 0; j < chromosomes.get(i).getGenes().length; j++) {
                chromosome+=chromosomes.get(i).getGenes()[j].getTransformation();    
            } 
            chromosome+="}"+"\n";           
        }
        return chromosome;
    }
  public Chromosome initializeChromosome(int size){
        
    	Gene I=new Gene('I', "Induction");
        Gene P=new Gene('P', "Privatization");
        Gene A=new Gene('A', "Parallelization");
        Gene R=new Gene('R', "Reduction");
        Gene L=new Gene('L', "Loop-interchange");
        Gene G=new Gene('G', "RangeAnalysis");
        
        Gene[] genes = new Gene[size];
        genes[0]=I;
        genes[1]=P;
        genes[2]=A;
        genes[3]=R;
        genes[4]=L;
        genes[5]=G;
        
        Chromosome chromosome= new Chromosome(genes, 0);

       return chromosome;
           
     }
        

   // public Chromosome initializeChromosome(){
   //   
   // 	Gene I=new Gene('I', "Induction");
   //     Gene P=new Gene('P', "Privatization");
   //     Gene A=new Gene('A', "Parallelization");
   //     Gene R=new Gene('R', "Reduction");
   //     Gene L=new Gene('L', "Loop-interchange");
   //     Gene G=new Gene('G', "RangeAnalysis");
   //     
   //     Gene[] genes = new Gene[SIZE];
   //     genes[0]=I;
   //     genes[1]=P;
   //     genes[2]=A;
   //     genes[3]=R;
   //     genes[4]=L;
   //     genes[5]=G;
   //
   //     Chromosome chromosome= new Chromosome(genes, 0);  
   //         return chromosome;
   //       }
   //     

   //ublic boolean existe(Gene[] genes, char b)
   //
   //
   //    boolean exist=false;
   //   for (int i = 0; i < genes.length&&!exist; i++) {
   //       if(genes[i]!=null&&genes[i].getTransformation()==b)
   //       {
   //         exist=true;
   //       }
   //   }
   //  
   //  return exist;
   //
    
    public boolean exist(Gene[] genes, String nameTransformation) {
    	
    	boolean exist=false;
    	String[] trans=nameTransformation.split("-");
    	for (int i = 0; i < genes.length&&!exist; i++) {
    		
             if(genes[i]!=null)            		
             {
            	 String [] renew=genes[i].getName().split("-"); 
            	 if(trans[0].toString().equals(renew[0].toString()))
            	 {
                   exist=true;
                   }
            }
        }
       
       return exist;
    	
    }



}