package CPU;

public class Register {
	String name;
	long value=0x00000000L;
	Flags f;
	
public Register(String name, Flags flags ) {
	// TODO Auto-generated constructor stub
	this.name=name;
	this.f=flags;
}
public String toString() {
	// TODO Auto-generated method stub
	StringBuffer sb=new StringBuffer(Long.toHexString(value));
	for (; sb.length() < 8; ) {
		sb.append("0");
	}
	return name+"-"+"0x"+sb;
}
public void add(long val){
	long g=value+val;
	if(g>0xFFFFFFFFL){
		
		f.setFlag((byte)0,true);
		g=g^(1L<<32L);
		
	}else {
		f.setFlag((byte)0,false);
	}
	
if(g>(1L<<31L)&&value<(1L<<31L)){
		
		f.setFlag((byte)11,true);
	}
else{
	f.setFlag((byte)11,false);
}
if(g==0){
	f.setFlag((byte)6,true);
}else{
	f.setFlag((byte)6,false);
}

if((g&(1L>>31L))!=0){
	f.setFlag((byte)6,false);
}else{
	
}
	
	
}

}
