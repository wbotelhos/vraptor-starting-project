package br.com.wbotelhos.starting.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;

@Component
public class CustomJSONSerialization extends XStreamJSONSerialization {

	public CustomJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer, XStreamBuilder builder) {
		super(response, extractor, initializer, builder);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected XStream getXStream() {
		XStream xstream = super.getXStream();

		xstream.registerConverter(new CollectionConverter(xstream.getMapper()) {

			@Override
			public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
				return Collection.class.isAssignableFrom(type) || List.class.isAssignableFrom(type) || ArrayList.class.isAssignableFrom(type);
			}
		});

		return xstream;
	}

}
