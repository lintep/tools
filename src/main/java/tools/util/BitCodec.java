package tools.util;

public class BitCodec {
	
	public static void main(String[] args) throws Exception {
		String result;
		double d = 0.9;
		result=Double.toHexString(d);//BinaryString(d);
		Logger.log("double:"+d+"\tround:"+Double.longBitsToDouble(Double.doubleToLongBits(d) & 0xFFFFFF0000000000L)+"\tresult:"+result+"\tlongbit:"+Double.doubleToLongBits(d));
		d=1;
		result=Double.toHexString(d);//BinaryString(d);
		Logger.log("double:"+d+"\tround:"+Double.longBitsToDouble(Double.doubleToLongBits(d) & 0xFFFFFF0000000000L)+"\tresult:"+result+"\tlongbit:"+Double.doubleToLongBits(d));
		d=1.1;
		result=Double.toHexString(d);//BinaryString(d);
		Logger.log("double:"+d+"\tround:"+Double.longBitsToDouble(Double.doubleToLongBits(d) & 0xFFFFFF0000000000L)+"\tresult:"+result+"\tlongbit:"+Double.doubleToLongBits(d));
		
//		int x1=1254;
//		int x2=452156;
//		int x3=1234567;
//		BitCodec bitCodec=new BitCodec();
//		System.out.println("input:\t"+x1+"\t"+x2+"\t"+x3);
//		long codedValue=bitCodec.encode(x1, x2, x3, 21);
//		System.out.println("coded value:\t"+codedValue);
//		ThreeInt threeLong = bitCodec.decode(codedValue,21);
//		System.out.println("output:\t"+threeLong.getInt1()+"\t"+threeLong.getInt2()+"\t"+threeLong.getInt3());
	}
	
	public long encode(long x1,long x2,long x3,int bitCount) throws Exception {
		if(bitCount>21)
			throw new Exception("bit count > 21 couse to create value bigger than long");
		double maxValue = Math.pow(2.0, bitCount);
		if(x1>maxValue || x2>maxValue || x3>maxValue)
			throw new Exception("less then one of the input argument can't encode with "+bitCount+" bit count(maxValue:"+maxValue+")");
//		System.out.println("encode:"+x1+"\t"+x2+"\t"+x3);
		long result=0;
//		System.out.println(Long.toBinaryString(0xFFFFFFFF & x1));
		result=result | (x1 << (2*bitCount));
//		System.out.println("add x1:"+Long.toBinaryString(0xFFFFFFFF & result));
//		System.out.println(Long.toBinaryString(0xFFFFFFFF & x2));
		result=result | (x2 << bitCount);
//		System.out.println("add x2:"+Long.toBinaryString(0xFFFFFFFF & result));
		result=result | (x3);
//		System.out.println("add x3:"+Long.toBinaryString(0xFFFFFFFF & result));
		return result;
	}

	public ThreeInt decode(long codedLong,int bitCount) {
		long y1,y2,y3;
		y3=codedLong;
		y3= 0x001FFFFF & codedLong;
//		System.out.println("x3:"+Long.toBinaryString(0xFFFFFFFF & y3));
		y2= codedLong>>bitCount;
		y2= 0x001FFFFF & y2;
//		System.out.println("x2:"+Long.toBinaryString(0xFFFFFFFF & y2));
		y1= codedLong>>(2*bitCount);
		y1= 0x001FFFFF & y1;
//		System.out.println("x1:"+Long.toBinaryString(0xFFFFFFFF & (Long)y1));
//		System.out.println("decode:"+y1+"\t"+y2+"\t"+y3);
		return new ThreeInt(y1,y2,y3);
	}
	
	public long revers(long codedLong, int bitCount) throws Exception {
		ThreeInt threeInt = decode(codedLong, bitCount);		
		return encode(threeInt.getInt3(), threeInt.getInt2(), threeInt.getInt3(), bitCount);
	}
	
	long resultInTrigram=0;
	public void setEncodeToken1Token2InTrigram(long x1, long x2,
			int bitCount) {
		resultInTrigram=0;
		resultInTrigram=resultInTrigram | (x1 << (2*bitCount));
		resultInTrigram=resultInTrigram | (x2 << bitCount);
	}
	public long getTrigramCodeForToken3(long x3,
			int bitCount) {
		return resultInTrigram | (x3);
	}
	
	public void setEncodeToken1Token3InTrigram(long x1, long x3,
			int bitCount) {
		resultInTrigram=0;
		resultInTrigram=resultInTrigram | (x1 << (2*bitCount));
		resultInTrigram=resultInTrigram | x3;
	}	
	public long getTrigramCodeForToken2(long x2,
			int bitCount) {
		return resultInTrigram | (x2 << bitCount);
	}
	
	public class ThreeInt{
		private int int1;
		private int int2;
		private int int3;
		
		public ThreeInt(long long1,long long2,long long3) {
			this.int1=(int)long1;
			this.int2=(int)long2;
			this.int3=(int)long3;
		}

		public int getInt1() {
			return int1;
		}
		public int getInt2() {
			return int2;
		}
		public int getInt3() {
			return int3;
		}
		
		public void println(){
			Logger.log(int1+"\t"+int2+"\t"+int3);
		}
		
		public long getDistictSortedCode(int bitCount) throws Exception{
			int x1=int1;
			int x2=int2;
			int x3=int3;

			int temp=0;
			
			if(x1>x2){
				temp=x2;
				x2=x1;
				x1=temp;
			}else if(x1==x2){
				x1=0;
			}
			
			if(x2>x3){
				temp=x3;
				x3=x2;
				x2=temp;
			}else if(x2==x3){
				x2=0;
			}
			
			if(x1>x2){
				temp=x2;
				x2=x1;
				x1=temp;
			}else if(x1==x2){
				x1=0;
			}
			
			return encode(x1, x2, x3, bitCount);
		}
	}

}
