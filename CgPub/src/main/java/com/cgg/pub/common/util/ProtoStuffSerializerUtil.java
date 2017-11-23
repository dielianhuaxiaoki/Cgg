package com.cgg.pub.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.DefaultIdStrategy;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * 序列话工具
 */
public class ProtoStuffSerializerUtil {

	
	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();


    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            synchronized (Schema.class) {
                if (schema == null) {
                    schema = RuntimeSchema.getSchema(clazz,
                            new DefaultIdStrategy());
                    cachedSchema.put(clazz, schema);
                }
            }
        }
        return schema;
    }

    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    public static <T> byte[] serializer(T obj) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @return
     */
    public static <T> T deserializer(byte[] data, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, obj, schema);
            return obj;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
	

	/**
	 * 序列化列表
	 * @param objList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> byte[] serializeList(List<T> objList) {
		if (objList == null || objList.isEmpty()) {
			throw new RuntimeException("序列化对象列表(" + objList + ")参数异常!");
		}
		
		Schema<T> schema = (Schema<T>) getSchema(objList.get(0).getClass());
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		byte[] protostuff = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			ProtostuffIOUtil.writeListTo(bos, objList, schema, buffer);
			protostuff = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
//			throw new RuntimeException("序列化对象列表(" + objList + ")发生异常!", e);
		} finally {
			buffer.clear();
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return protostuff;
	}

    /**
     * 反序列化list<T>
     * 
     * @param listByte
     * @param clazz
     * @return
     */
    public static <T> List<T> deserializeList(byte[] listByte, Class<T> clazz) {
        if (listByte == null || listByte.length == 0) {
            throw new RuntimeException("反序列化list对象发生异常，byte为空");
        }
        Schema<T> schema = (Schema<T>) getSchema(clazz);
        List<T> result = null;
        ByteArrayInputStream in = null;
        try {
            in = new ByteArrayInputStream(listByte);
            result = ProtostuffIOUtil.parseListFrom(in, schema);
        } catch (Exception e) {
            throw new RuntimeException("反序列化对象列表 list发生异常!", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
	
}