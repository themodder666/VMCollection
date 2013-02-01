package CPU;

public class Flags {
	 long eflags=0L;
public Flags(int eflags) {
	// TODO Auto-generated constructor stub
	this.eflags=eflags;
}
public void setFlag(byte orig, boolean b){
	
	if(b){
		eflags=(eflags|(1<<orig));
	}else {
		eflags=(eflags&(0<<orig));
	}
}
public boolean getFlag(byte pos){
	return (eflags&1<<pos)!=0;
	
}
}
