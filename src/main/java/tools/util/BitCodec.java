package tools.util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BitCodec {

	public static final int LONGBYTES = 8;

	public static void main(String[] args) throws Exception {
		int x1=1;
		int x2=1;
		int code = BitCodec.encodeIntImBalance(x1, x2, 17);
		System.out.println(code);
		TwoInt decode = decodeIntImBalance(code, 17);
		System.out.println(decode.int1+"\t"+decode.int2);
		// String result;
		// double d = 0.9;
		// result=Double.toHexString(d);//BinaryString(d);
		// Logger.log("double:"+d+"\tround:"+Double.longBitsToDouble(Double.doubleToLongBits(d)
		// &
		// 0xFFFFFF0000000000L)+"\tresult:"+result+"\tlongbit:"+Double.doubleToLongBits(d));
		// d=1;
		// result=Double.toHexString(d);//BinaryString(d);
		// Logger.log("double:"+d+"\tround:"+Double.longBitsToDouble(Double.doubleToLongBits(d)
		// &
		// 0xFFFFFF0000000000L)+"\tresult:"+result+"\tlongbit:"+Double.doubleToLongBits(d));
		// d=1.1;
		// result=Double.toHexString(d);//BinaryString(d);
		// Logger.log("double:"+d+"\tround:"+Double.longBitsToDouble(Double.doubleToLongBits(d)
		// &
		// 0xFFFFFF0000000000L)+"\tresult:"+result+"\tlongbit:"+Double.doubleToLongBits(d));

		// int bitCount=21;
		// long maxVal=(long)Math.pow(2.0,bitCount)-1;
		// long code = new BitCodec().encode(0, maxVal, 0, bitCount);
		// System.out.println(code+"("+Math.ceil(Math.ceil(Math.log10(code)/Math.log10(2.0))/bitCount)+")"+"\t"+BitCodec.getTokenCodeCount(code,
		// bitCount));

		// testByteArray();

//
//		longArrayToBytesTest();
//
//
		// int x1=1254;
		// int x2=452156;
		// int x3=1234567;
		// BitCodec bitCodec=new BitCodec();
		// System.out.println("input:\t"+x1+"\t"+x2+"\t"+x3);
		// long codedValue=bitCodec.encode(x1, x2, x3, 21);
		// System.out.println("coded value:\t"+codedValue);
		// ThreeInt threeLong = bitCodec.decode(codedValue,21);
		// System.out.println("output:\t"+threeLong.getInt1()+"\t"+threeLong.getInt2()+"\t"+threeLong.getInt3());
	}

	private static void longArrayToBytesTest() throws Exception {
		Random random=new Random();
		
		int count=32;
		ArrayList<Long> encodedLong=new ArrayList<Long>();
		
		int bitcount = 21;
		
		int maxKey=(int)(Math.pow(2, bitcount))-1;
//		long maxScore=(long)(Math.pow(2, 64-bitcount))-1;
		for (int i = 0; i < count; i++) {
			int key = random.nextInt(maxKey);
			int score = random.nextInt();
			long encodedKeyValue = BitCodec.encodeToLong(score, key, bitcount);
			encodedLong.add(encodedKeyValue);
			System.out.println(i+": "+key + " , "+score+" -> "+encodedKeyValue);
		}
		System.out.println("________________________");
		byte[] arrayToBytes = BitCodec.arrayToBytes(encodedLong);
		
		long[] bytesToArray = BitCodec.bytesToLongList(arrayToBytes);
		for (int j = 0; j < bytesToArray.length; j++) {
			long codedLong = bytesToArray[j];
			LongLong longLong = BitCodec.decodeToLong(codedLong, bitcount);
			System.out.println(j+") "+longLong.rightLong + " , "+longLong.leftLong+" -> "+codedLong);
		}
		
	}

	private static void testByteArray() {
		long x1 = 1234567891;
		long x2 = 1234567892;
		long x3 = 1234567893;

		byte[] x13 = new byte[3 * LONGBYTES];

		byte[] x1L = BitCodec.longToBytes(x1);
		int k = 0;
		for (int i = 0; i < x1L.length; i++) {
			x13[i + k] = x1L[i];
		}

		byte[] x2L = BitCodec.longToBytes(x2);
		k = 8;
		for (int i = 0; i < x1L.length; i++) {
			x13[i + k] = x2L[i];
		}

		byte[] x3L = BitCodec.longToBytes(x3);
		k = 16;
		for (int i = 0; i < x1L.length; i++) {
			x13[i + k] = x3L[i];
		}

		String str = new String(x13);
		byte[] y13 = str.getBytes();
		System.out.println("string )" + " [" + str.length() + "] -> "
				+ y13.length);

		k = 0;
		for (int i = 0; i < y13.length; i += LONGBYTES) {
			byte[] temp = new byte[LONGBYTES];
			for (int j = 0; j < LONGBYTES; j++) {
				temp[j] = y13[i + j];
			}
			System.out.println(++k + ") " + BitCodec.bytesToLong(temp));
		}
	}

	public static int getTokenCodeCount(long code, int bitCount) {
		// if(code==0)
		// return 0;
		//
		// if(code==1)
		// return 1;

		int result = (int) Math.ceil(Math.ceil(Math.log10(code + 1)
				/ Math.log10(2.0))
				/ bitCount);
		// if(result)
		return result;
	}

	public static long encode(long x1, long x2, long x3, int bitCount)
			throws Exception {
		if (bitCount > 21)
			throw new Exception(
					"bit count > 21 couse to create value bigger than long");
		double maxValue = Math.pow(2.0, bitCount) - 1;
		if (x1 > maxValue || x2 > maxValue || x3 > maxValue)
			throw new Exception(
					"At least one of the input argument can't encode with "
							+ bitCount + " bit count(maxValue:" + maxValue
							+ ")");
		// System.out.println("encode:"+x1+"\t"+x2+"\t"+x3);
		long result = 0;
		// System.out.println(Long.toBinaryString(0xFFFFFFFF & x1));
		result = result | (x1 << (2 * bitCount));
		// System.out.println("add x1:"+Long.toBinaryString(0xFFFFFFFF &
		// result));
		// System.out.println(Long.toBinaryString(0xFFFFFFFF & x2));
		result = result | (x2 << bitCount);
		// System.out.println("add x2:"+Long.toBinaryString(0xFFFFFFFF &
		// result));
		result = result | (x3);
		// System.out.println("add x3:"+Long.toBinaryString(0xFFFFFFFF &
		// result));
		return result;
	}

	public static long encode(long x2, long x3, int bitCount) throws Exception {
		return encode(0, x2, x3, bitCount);
	}

	public static int encodeInt(int x1, int x2, int bitCount) throws Exception {
		if (bitCount > 16)
			throw new Exception(
					"bit count > 16 cause to create value bigger than int");
		double maxValue = Math.pow(2.0, bitCount) - 1;
		if (x1 > maxValue || x2 > maxValue)
			throw new Exception(
					"At least one of the input argument can't encode with "
							+ bitCount + " bit count(maxValue:" + maxValue
							+ ")");
		int result = 0;
		result = result | (x1 << bitCount);
		result = result | (x2);
		return result;
	}

	public static int encodeIntImBalance(int x1, int x2, int leftBitCount) throws Exception {
		double leftMaxValue = Math.pow(2.0, leftBitCount) - 1;
		double rightMaxValue = Math.pow(2.0, 32-leftBitCount) - 1;
		if (x1 > leftMaxValue)
			throw new Exception(
					"Input argument "+x1+" can't encode with "
							+ leftBitCount + " bit count(maxValue:" + leftMaxValue+ ")");
		if (x2 > rightMaxValue)
			throw new Exception(
					"Input argument "+x2+" can't encode with "
							+ (32-leftBitCount) + " bit count(maxValue:" + rightMaxValue+ ")");
		int result = 0;
		result = result | (x1 << (32-leftBitCount));
		result = result | (x2);
		return result;
	}

	public static long encode(long x3, int bitCount) throws Exception {
		return encode(0, 0, x3, bitCount);
	}

	public static ThreeInt decode(long codedLong, int bitCount) {
		long y1, y2, y3;
		y3 = codedLong;
		y3 = 0x001FFFFF & codedLong;
		// System.out.println("x3:"+Long.toBinaryString(0xFFFFFFFF & y3));
		y2 = codedLong >> bitCount;
		y2 = 0x001FFFFF & y2;
		// System.out.println("x2:"+Long.toBinaryString(0xFFFFFFFF & y2));
		y1 = codedLong >> (2 * bitCount);
		y1 = 0x001FFFFF & y1;
		// System.out.println("x1:"+Long.toBinaryString(0xFFFFFFFF & (Long)y1));
		// System.out.println("decode:"+y1+"\t"+y2+"\t"+y3);
		ThreeInt thrigram = null;
		thrigram = new ThreeInt(y1, y2, y3);
		return thrigram;
	}

	public static TwoInt decodeInt(int codedInt, int bitCount) {
		int y1, y2;
		y2 = codedInt;
		y2 = 0x0000FFFF & codedInt;//?????? 0x0000FFFF independent from bitCount why?

		y1 = codedInt >> bitCount;
		y1 = 0x0000FFFF & y1;

		TwoInt twoInt = null;
		twoInt = new TwoInt(y1, y2);
		return twoInt;
	}

	public static TwoInt decodeIntImBalance(int codedInt, int bitCount) {
		int y1, y2;
		y2 = ((1<<(32-bitCount))-1) & codedInt;//?????? 0x0000FFFF independent from bitCount why?

		y1 = codedInt >> (32-bitCount);
		y1 = ((1<<bitCount)-1) & y1;

		TwoInt twoInt = null;
		twoInt = new TwoInt(y1, y2);
		return twoInt;
	}

	public static long revers(long codedLong, int bitCount) throws Exception {
		ThreeInt threeInt = decode(codedLong, bitCount);
		return encode(threeInt.getInt3(), threeInt.getInt2(),
				threeInt.getInt3(), bitCount);
	}

	public static void setEncodeToken1Token2InTrigram(long x1, long x2,
			int bitCount) {
		long resultInTrigram = 0;
		resultInTrigram = resultInTrigram | (x1 << (2 * bitCount));
		resultInTrigram = resultInTrigram | (x2 << bitCount);
	}

	long resultInTrigram = 0;

	public long getTrigramCodeForToken3(long x3, int bitCount) {
		return resultInTrigram | (x3);
	}

	public void setEncodeToken1Token3InTrigram(long x1, long x3, int bitCount) {
		resultInTrigram = 0;
		resultInTrigram = resultInTrigram | (x1 << (2 * bitCount));
		resultInTrigram = resultInTrigram | x3;
	}

	public long getTrigramCodeForToken2(long x2, int bitCount) {
		return resultInTrigram | (x2 << bitCount);
	}

	public static class ThreeInt {
		private int int1;
		private int int2;
		private int int3;

		public ThreeInt(long long1, long long2, long long3) {
			this.int1 = (int) long1;
			this.int2 = (int) long2;
			this.int3 = (int) long3;
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

		public void println() {
			Logger.log(int1 + "\t" + int2 + "\t" + int3);
		}

		public long getDistinctSortedCode(int bitCount) throws Exception {
			int x1 = int1;
			int x2 = int2;
			int x3 = int3;

			int temp = 0;

			if (x1 > x2) {
				temp = x2;
				x2 = x1;
				x1 = temp;
			} else if (x1 == x2) {
				x1 = 0;
			}

			if (x2 > x3) {
				temp = x3;
				x3 = x2;
				x2 = temp;
			} else if (x2 == x3) {
				x2 = 0;
			}

			if (x1 > x2) {
				temp = x2;
				x2 = x1;
				x1 = temp;
			} else if (x1 == x2) {
				x1 = 0;
			}

			return encode(x1, x2, x3, bitCount);
		}
	}

	public static class TwoInt {
		private int int1;
		private int int2;

		public TwoInt(int int1, int int2) {
			this.int1 = int1;
			this.int2 = int2;
		}

		public int getInt1() {
			return int1;
		}

		public int getInt2() {
			return int2;
		}


		public String toString() {
			return int1 + "\t" + int2;
		}

		public int getDistinctSortedCode(int bitCount) throws Exception {
			int x1 = int1;
			int x2 = int2;

			int temp = 0;

			if (x1 > x2) {
				temp = x2;
				x2 = x1;
				x1 = temp;
			} else if (x1 == x2) {
				x1 = 0;
			}

			return encodeInt(x1, x2, bitCount);
		}
	}

	public static int encodeToInt(int xLeft, int xRight, int xRightBitCount)
			throws Exception {
		if (xRightBitCount > 32)
			throw new Exception(
					"encoded int bit should be equall or lower than 32.");

		double maxValue = Math.pow(2.0, 32 - xRightBitCount);
		if (xLeft > maxValue)
			throw new Exception("xLeft the input argument (" + xLeft
					+ ") can't encode with " + (32 - xRightBitCount)
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, xRightBitCount);
		if (xRight > maxValue)
			throw new Exception("xRight the input argument (" + xRight
					+ ") can't encode with " + xRightBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		int result = 0;
		result = result | (xLeft << xRightBitCount);
		// System.out.println("add x2:"+Long.toBinaryString(0xFFFFFFFF &
		// result));
		result = result | (xRight);
		// System.out.println("add x3:"+Long.toBinaryString(0xFFFFFFFF &
		// result));
		return result;
	}

	public static long encodeToLong(long xFirst, long xSecond,
			int xSecondBitCount, long xRight, int xRightBitCount)
			throws Exception {
		if (xRightBitCount > 64)
			throw new Exception(
					"encoded int bit should be equall or lower than 64.");

		double maxValue = Math.pow(2.0, xRightBitCount);

		if (xRight > maxValue)
			throw new Exception("xLeft the input argument (" + xRight
					+ ") can't encode with " + xRightBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, xSecondBitCount);
		if (xSecond > maxValue)
			throw new Exception("xSecond the input argument (" + xSecond
					+ ") can't encode with " + xSecondBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, 64 - xSecondBitCount - xRightBitCount);
		if (xFirst > maxValue)
			throw new Exception("xFirst the input argument (" + xFirst
					+ ") can't encode with "
					+ (64 - xSecondBitCount - xRightBitCount)
					+ " bit count(maxValue:" + maxValue + ")");

		long result = 0;
		result = result | (xFirst << (xSecondBitCount + xRightBitCount));
		result = result | (xSecond << xRightBitCount);
		result = result | (xRight);
		return result;
	}

	public static long encodeToLong(long xLeft, long xRight, int xRightBitCount)
			throws Exception {
		if (xRightBitCount > 64)
			throw new Exception(
					"encoded int bit should be equall or lower than 64.");

		double maxValue = Math.pow(2.0, xRightBitCount);

		if (xRight >= maxValue)
			throw new Exception("xRight the input argument (" + xRight
					+ ") can't encode with " + xRightBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, LONGBYTES * 8 - xRightBitCount);
		if (xLeft >= maxValue)
			throw new Exception("xLeft the input argument (" + xLeft
					+ ") can't encode with " + (LONGBYTES * 8 - xRightBitCount)
					+ " bit count(maxValue:" + maxValue + ")");

		long result = 0;
		result = result | (xLeft << (xRightBitCount));
		result = result | (xRight);
		return result;
	}

	public static LongLong decodeToLong(long encodedLong, int xRightBitCount)
			throws Exception {
//		System.out.println(Long.toBinaryString(encodedLong));
		long leftLong = (encodedLong >> (xRightBitCount));
		long maxValue = (long) Math.pow(2.0, xRightBitCount) - 1;
//		System.out.println(Long.toBinaryString(maxValue));
		long rightLong = encodedLong & maxValue;

		return new LongLong(leftLong, rightLong, xRightBitCount);
	}

	public static long encodeToLong(long xFirst, long xSecond,
			int xSecondBitCount, long xThird, int xThirdBitCount, long xRight,
			int xRightBitCount) throws Exception {
		if (xRightBitCount > 64)
			throw new Exception(
					"encoded int bit should be equall or lower than 64.");

		double maxValue = Math.pow(2.0, xRightBitCount);

		if (xRight > maxValue)
			throw new Exception("xRight the input argument (" + xRight
					+ ") can't encode with " + xRightBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, xThirdBitCount);
		if (xThird > maxValue)
			throw new Exception("xThird the input argument (" + xThird
					+ ") can't encode with " + xThirdBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, xSecondBitCount);
		if (xSecond > maxValue)
			throw new Exception("xSecond the input argument (" + xSecond
					+ ") can't encode with " + xSecondBitCount
					+ " bit count(maxValue:" + maxValue + ")");

		maxValue = Math.pow(2.0, 64 - xThirdBitCount - xSecondBitCount
				- xRightBitCount);
		if (xFirst > maxValue)
			throw new Exception("xFirst the input argument (" + xFirst
					+ ") can't encode with "
					+ (64 - xThirdBitCount - xSecondBitCount - xRightBitCount)
					+ " bit count(maxValue:" + maxValue + ")");

		long result = 0;
		result = result
				| (xFirst << (xThirdBitCount + xSecondBitCount + xRightBitCount));
		result = result | (xSecond << (xSecondBitCount + xRightBitCount));
		result = result | (xThird << xRightBitCount);
		result = result | (xRight);
		return result;
	}

	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(LONGBYTES);
		buffer.putLong(x);
		return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(LONGBYTES);
		buffer.put(bytes);
		buffer.flip();// need flip
		return buffer.getLong();
	}

	public static byte[] arrayToBytes(List<Long> longList) {
		byte[] result = new byte[longList.size() * LONGBYTES];

		int counter = 0;
		for (long encodedLong : longList) {
			byte[] longByte = longToBytes(encodedLong);
			for (int j = 0; j < LONGBYTES; j++) {
				result[counter * LONGBYTES + j] = longByte[j];
			}
			counter++;
		}

		return result;
	}
	
	
	public static long[] bytesToLongList(byte[] encodedLongByteArray) {
		int resultCount = encodedLongByteArray.length/LONGBYTES;
		long[] result = new long[resultCount];

		byte[] tempLongByte = new byte[LONGBYTES];
		for (int i=0; i< resultCount;i++) {
			
			for (int j = 0; j < LONGBYTES; j++) {
				tempLongByte[j] = encodedLongByteArray[i*LONGBYTES+j];
			}
			
			long encodedLong= BitCodec.bytesToLong(tempLongByte);
			
			result[i]=encodedLong;
		}

		return result;
	}

	public static class LongLong {
		public int rightBitCount;
		public long leftLong;
		public long rightLong;

		public LongLong(long leftLong, long rightLong, int rightBitCount) {
			this.leftLong = leftLong;
			this.rightLong = rightLong;
			this.rightBitCount = rightBitCount;
		}
	}
}
