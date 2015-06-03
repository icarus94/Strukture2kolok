import java.util.LinkedList;


public class BinarnoStablo {
	
	public class CvorBS{
		public int podatak;
		public CvorBS levo,desno;
		
		public CvorBS(int p, CvorBS l, CvorBS d){
			podatak = p;
			levo = l;
			desno = d;
		}
		public CvorBS(int p){
			// poziva prethodni konstruktor i prosledjuje mu odgovarajuce parametre
			this(p, null, null);
		}
	}
	public CvorBS koren;
	
	public static void main(String[]arg){
		BinarnoStablo.CvorBS node1,node2,node3,node4,node5,node6,node7,node8;
		BinarnoStablo stablo = new BinarnoStablo();
		
		
		node1=stablo.new CvorBS(1);
		node2=stablo.new CvorBS(2);
		node3=stablo.new CvorBS(3);
		node4=stablo.new CvorBS(4);
		node5=stablo.new CvorBS(5);
		node6=stablo.new CvorBS(6);
		node7=stablo.new CvorBS(7);
		node8=stablo.new CvorBS(8);
		
		node4.levo=node3;
		node4.desno=node6;
		node6.levo=node5;
		node6.desno=node8;
		node8.desno=node7;
		node3.levo=node2;
		node3.desno=node1;
		
		stablo.koren=node4;
		
		//stablo.stampajDoNajveceg(node4);
		stablo.odPdoQ(node4, node6, node7);
		
	}
	
	
	//Stampa cvorove binarnog stabla od korena do cvora koji ima najvecu vrednost u stablu
	
	public void stampajDoNajveceg(CvorBS prvi){
		if(prvi==null)
			return;
		if(prvi.podatak==maxVrednost(prvi)){
			System.out.println(prvi.podatak);
			return;
		}
		if(maxVrednost(prvi.levo)>maxVrednost(prvi.desno)){
			System.out.println(prvi.podatak);
			stampajDoNajveceg(prvi.levo);
		}else{
			System.out.println(prvi.podatak);
			stampajDoNajveceg(prvi.desno);
		}
	}
	
	public int maxVrednost(CvorBS prvi){
		if(prvi==null)
			return Integer.MIN_VALUE;
		return Math.max(prvi.podatak,Math.max(maxVrednost(prvi.levo), maxVrednost(prvi.desno)));
	}


	/**
	 * Dat je pokazivac na koren binarnog stabla ciji cvorovi sadrže cele brojeve. Napisite funkcij
	 * u koja ce vratiti broj cvorova koji su po sadrzaju veci od sadrzaja svih svojih potomoka.
	 */
	
	public int prebrojVecihOdPotomaka(CvorBS prvi){
		if(prvi==null)
			return 0;
		int a=0;
		if(prvi.levo==null && prvi.desno==null){
			return 0;
		}
		if(prvi.levo==null && prvi.desno!=null){
			if( prvi.podatak>prvi.desno.podatak){
				a =1+prebrojVecihOdPotomaka(prvi.desno); 
			}else{
				a =0+prebrojVecihOdPotomaka(prvi.desno);
			}
		}else if(prvi.levo!=null && prvi.desno==null){
			if(prvi.podatak>prvi.levo.podatak && prvi.podatak>prvi.desno.podatak){
				a =1+prebrojVecihOdPotomaka(prvi.levo); 
			}else{
				a =0+prebrojVecihOdPotomaka(prvi.levo);	
			}
			
		}else{
			if(prvi.podatak>prvi.levo.podatak && prvi.podatak>prvi.desno.podatak){
				a =1+prebrojVecihOdPotomaka(prvi.desno)+prebrojVecihOdPotomaka(prvi.levo); 
			}else{
				a =0+prebrojVecihOdPotomaka(prvi.desno)+prebrojVecihOdPotomaka(prvi.levo);	
			}
		}	
		
		return a;
	}
	
	public void odPdoQ(CvorBS prvi,CvorBS p,CvorBS q){
		if(prvi==null || p==null || q==null)
			return;
		LinkedList<CvorBS> odPdoRoot = new LinkedList<CvorBS>();
		LinkedList<CvorBS> odQdoRoot = new LinkedList<CvorBS>();
		toTheRoot(prvi,p,odPdoRoot);
		toTheRoot(prvi,q,odQdoRoot);
		int j=odQdoRoot.size()-1;
		
		for(int i=odPdoRoot.size()-1;i>=0;i--){
			if(odPdoRoot.get(i)!=odQdoRoot.get(j--)){
				prvi=odPdoRoot.get(i+1);
				break;
			}
			if(i==0)
				prvi=odPdoRoot.get(i);
		}
		for(int i=0;i<odPdoRoot.size();i++){
			System.out.println(odPdoRoot.get(i).podatak);
			if(odPdoRoot.get(i)==prvi)
				break;
		}
		while(j!=-1){
			System.out.println(odQdoRoot.get(j--).podatak);
		}	
	}
	public void toTheRoot(CvorBS prvi,CvorBS neki,LinkedList<CvorBS> list){
		if(prvi==null || neki==null)
			return;
		list.addLast(neki);
		toTheRoot(prvi, nadjiRoditelja(prvi,neki), list);
	}
	public CvorBS nadjiRoditelja(CvorBS pointerR,CvorBS neki){
		if(pointerR==null || neki==pointerR)
			return null;
		if((pointerR.levo==neki || pointerR.desno==neki)){
			return pointerR;
		}
		if(nadjiRoditelja(pointerR.desno, neki)==null)
			return nadjiRoditelja(pointerR.levo, neki);
		return nadjiRoditelja(pointerR.desno, neki);
	}

}
