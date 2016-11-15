package buttondevteam.chat.formatting;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.*;
import com.google.gson.stream.*;

import buttondevteam.lib.chat.TellrawSerializableEnum;

public abstract class TellrawSerializer {
	public static class TwEnum extends TypeAdapter<TellrawSerializableEnum> {
		@Override
		public TellrawSerializableEnum read(JsonReader reader) throws IOException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void write(JsonWriter writer, TellrawSerializableEnum enumval) throws IOException {
			if (enumval == null)
				writer.nullValue();
			else
				writer.value(enumval.getName());
		}
	}

	public static class TwCollection implements JsonSerializer<Collection<?>> {
		@Override
		public JsonElement serialize(Collection<?> src, Type typeOfSrc, JsonSerializationContext context) {
			if (src == null || src.isEmpty())
				return null;

			JsonArray array = new JsonArray();

			for (Object child : src) {
				JsonElement element = context.serialize(child);
				array.add(element);
			}

			return array;
		}
	}

	public static class TwBool extends TypeAdapter<Boolean> {
		@Override
		public Boolean read(JsonReader reader) throws IOException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void write(JsonWriter writer, Boolean val) throws IOException {
			if (val)
				writer.value(val);
			else
				writer.nullValue();
		}
	}
}
