package CPU;

public class CPU {
Flags flags=new Flags(0);
Register eax=new Register("eax", flags), ebx=new Register("ebx", flags), ecx=new Register("ecx", flags), edx=new Register("edx", flags);
byte[] memory;
public CPU(byte[] memory) throws NotEnoughMemoryException {
	// TODO Auto-generated constructor stub
	this.memory=memory;
	if(memory.length<1048576){
		throw new NotEnoughMemoryException();
	}
	
	
}	
public Register[] getRegisters(){
	return new Register[]{eax,ebx,ecx,edx};
}
public void process_Opcode(byte opcode, boolean isTwoBit,byte mod, byte sib, byte[] displacement, byte[] immediate  ){
		if(!isTwoBit){
			for (int i = 0; i < immediate.length; i++) {
				
			}
		}
	}
	public void add(byte opcode, boolean isTwoBit,byte mod, byte sib, byte[] displacement, byte[] immediate){
		if((opcode&1)==1){
			if((opcode&0x10b)!=0){
				
			}else {
				
			}
		}else {
			if((opcode&0x10b)!=0){
				
			}else {
				
			}
		}
	}
	
}
