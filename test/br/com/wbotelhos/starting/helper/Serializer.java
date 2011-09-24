package br.com.wbotelhos.starting.helper;

import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

public class Serializer {

	private static final char[] DEFAULT_LINE_INDENTER = {};

	public static String serialize(Object obj) {
		return getXStreamJSON().toXML(obj);
	}

	private static XStream getXStreamJSON() {
		XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {

			@Override
			public HierarchicalStreamWriter createWriter(Writer writer) {
				return new JsonWriter(writer, DEFAULT_LINE_INDENTER, "", JsonWriter.DROP_ROOT_MODE);
			}
		});

		return xstream;
	}
}
