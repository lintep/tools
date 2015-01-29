package tools.util;

import java.util.HashSet;
import java.util.Set;

public class NgramString {

	private static final int TERM_MAX_LENGTH = 30;
	String baseString;
	int splitterCount;
	int splitterIndex[];
	int maxSplitterCount;
	StringBuilder tempStringBuilder;
	int nullTokenCount;
	Set<String> ligalTokens;

	public NgramString(String string, int maxSplitterCount,
			Set<String> ligalTokens) {
		this(string, maxSplitterCount);
		this.ligalTokens = new HashSet<String>(ligalTokens);
	}

	public NgramString(String string, int maxSplitterCount) {
		this.maxSplitterCount = maxSplitterCount;
		this.splitterIndex = new int[this.maxSplitterCount];
		this.splitterIndex[0] = -1;
		this.tempStringBuilder = new StringBuilder(maxSplitterCount);
		reload(string);
	}

	public void reload(String string) {
		this.baseString = string;
		this.splitterCount = 0;
		int tempEndIndex = -1;
		this.nullTokenCount = 0;
		for (int i = 0; i < baseString.length(); i++) {
			if (isSplitter(i)) {
				if (tempEndIndex + 1 == i) {
					tempEndIndex = i;
					nullTokenCount++;
					// continue;
				}
				splitterCount++;
				this.splitterIndex[splitterCount] = i;
				tempEndIndex = i;
				if(i==baseString.length()-1)
					splitterCount--;
			}
		}
		this.splitterIndex[splitterCount+1] = string.length();
	}

	private boolean isSplitter(int i) {
		return this.baseString.charAt(i) == ' ';
	}

	public int getNgramCount(int kgram) {
		if (kgram <= 0 || splitterCount + 1 - kgram <= 0)
			return 0;
		return splitterCount + 1 - kgram + 1 - nullTokenCount;
	}

	public String getKgram(int kgram, int termIndex) {
		// this.tempStringBuilder.setLength(0);
		// this.tempStringBuilder.append(baseString.substring(this.splitterIndex[termIndex]+1,
		// this.splitterIndex[termIndex+kgram]-1));
		getKgram(this.tempStringBuilder, kgram, termIndex);
	return this.tempStringBuilder.toString();
	}

	// StringBuilder tempTermStringBuilder=new StringBuilder(TERM_MAX_LENGTH);
	StringBuffer tempTermStringBuffer = new StringBuffer(TERM_MAX_LENGTH);

	public void getKgram(StringBuilder stringBuilder, int kgram, int termIndex) {
		stringBuilder.setLength(0);
		int startIndex = 0;
		int endIndex = 0;
		int t = 0;
		int k = 0;
		for (int i = 0; i < kgram; i++) {
			while (true) {
				t = i + k;
				startIndex = this.splitterIndex[termIndex + t] + 1;
//				if(startIndex==)
				endIndex = this.splitterIndex[termIndex + t + 1];
				k++;
				if (endIndex - startIndex >= 1)
					break;
			}
			k--;
			tempTermStringBuffer.setLength(0);
			for (int j = startIndex; j < endIndex; j++) {
				tempTermStringBuffer.append(this.baseString.charAt(j));
			}

			if (this.ligalTokens != null) {
				if (!this.ligalTokens.contains(tempTermStringBuffer.toString())) {
					stringBuilder.setLength(0);
					return;
				}
			}

			stringBuilder.append(tempTermStringBuffer);
			stringBuilder.append(" ");
		}
		stringBuilder.setLength(stringBuilder.length() - 1);
	}

	public void getKgram(int kgram) {
		tempStringBuilder.setLength(0);
		for (int termIndex = 0; termIndex < getNgramCount(kgram); termIndex++) {
			getKgram(tempStringBuilder, kgram, termIndex);
			if(tempStringBuilder.length()>0)
				write(tempStringBuilder.toString());
		}
	}

	protected void write(String string) {
		Logger.log(string);
	}
}
