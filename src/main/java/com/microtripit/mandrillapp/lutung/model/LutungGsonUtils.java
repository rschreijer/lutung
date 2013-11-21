/**
 * 
 */
package com.microtripit.mandrillapp.lutung.model;

import com.google.gson.*;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author rschreijer
 * @since Mar 16, 2013
 */
public final class LutungGsonUtils {
	private static final String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
	
	private static Gson gson = createGson();
	
	public static final Gson getGson() {
		return gson;
	}
	
	public static final Gson createGson() {
		return createGsonBuilder().create();
	}
	
	public static final GsonBuilder createGsonBuilder() {
		return new GsonBuilder()
				.setDateFormat(dateFormatStr)
				.registerTypeAdapter(Date.class, new DateDeserializer())
				.registerTypeAdapter(Map.class, new MapSerializer())
				.registerTypeAdapter(MandrillMessage.Recipient.Type.class, new RecipientTypeSerializer())
				.registerTypeAdapter(MandrillMessageStatus.Status.class, new MessageStatusSerializer());
	}
	
	public static final class DateDeserializer 
			implements JsonDeserializer<Date>, JsonSerializer<Date> {
		
		private final SimpleDateFormat formatter;
		
		protected DateDeserializer() {
			formatter = new SimpleDateFormat(dateFormatStr);
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		
		public final Date deserialize(final JsonElement json, 
				final Type typeOfT, final JsonDeserializationContext context) 
						throws JsonParseException {
			
			if(!json.isJsonPrimitive()) {
				throw new JsonParseException(
						"Unexpected type for date: " +json.toString());
				
			}
			try {
				return formatter.parse(json.getAsString());
				
			} catch(final ParseException e) {
				throw new JsonParseException("Failed to parse date '" 
						+json.getAsString()+ "'", e);
				
			}
		}
		
		public JsonElement serialize(Date src, Type typeOfSrc,
				JsonSerializationContext context) {
			
			return new JsonPrimitive(formatter.format(src));
		}
	}
	
	public static class MapSerializer implements JsonSerializer<Map<? extends Object,? extends Object>> {
		
		public final JsonElement serialize(final Map<?, ?> src, 
				final Type typeOfSrc, final JsonSerializationContext context) {
			
			Object value;
			final JsonObject json = new JsonObject();
			for(Object key : src.keySet()) {
				value = src.get(key);
				json.add( key.toString(), context.serialize(
						value, value.getClass()) );
			}
			return json;
			
		}
		
	}

	public static final class RecipientTypeSerializer
			implements JsonDeserializer<MandrillMessage.Recipient.Type>, JsonSerializer<MandrillMessage.Recipient.Type> {

		public final MandrillMessage.Recipient.Type deserialize(final JsonElement json,
									  final Type typeOfT, final JsonDeserializationContext context)
				throws JsonParseException {
			if(!json.isJsonPrimitive()) {
				throw new JsonParseException(
						"Unexpected type for recipient type: " +json.toString());
			}

			return MandrillMessage.Recipient.Type.valueOf(json.getAsString().toUpperCase());

		}

		public JsonPrimitive serialize(MandrillMessage.Recipient.Type src, Type typeOfSrc,
									 JsonSerializationContext context) {

			return new JsonPrimitive(src.name().toLowerCase());
		}
	}

	public static final class MessageStatusSerializer
			implements JsonDeserializer<MandrillMessageStatus.Status>, JsonSerializer<MandrillMessageStatus.Status> {

		public final MandrillMessageStatus.Status deserialize(final JsonElement json,
																final Type typeOfT, final JsonDeserializationContext context)
				throws JsonParseException {
			if(!json.isJsonPrimitive()) {
				throw new JsonParseException(
						"Unexpected type for recipient type: " +json.toString());
			}

			return MandrillMessageStatus.Status.valueOf(json.getAsString().toUpperCase());

		}

		public JsonPrimitive serialize(MandrillMessageStatus.Status src, Type typeOfSrc,
									   JsonSerializationContext context) {

			return new JsonPrimitive(src.name().toLowerCase());
		}
	}

}
