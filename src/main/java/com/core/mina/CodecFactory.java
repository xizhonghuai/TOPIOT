package com.core.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CodecFactory implements ProtocolCodecFactory{
 
	private Charset decodecharset; // ���뷽ʽ
	private Charset encodecharset; // ���뷽ʽ
	 

	public CodecFactory(Charset decodecharset,Charset encodecharset) {

		this.decodecharset=decodecharset;
		this.encodecharset=encodecharset;
		
	}

	 

	@Override
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return  new PackDecoder(decodecharset);
	}
	
	
	
	@Override
	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return new PackEncoder(encodecharset);
	}

	


}