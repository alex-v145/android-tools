package com.macsoftex.android_tools.stream;

import android.content.Context;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by alex on 19.03.18.
 */

public class StreamTools {
    public static String readText(InputStream inputStream) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine()) != null) {
                if (stringBuilder.toString().length() != 0)
                    stringBuilder.append("\n");

                stringBuilder.append(line);
            }
        } catch (Exception e) {
            stringBuilder = null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (stringBuilder != null)
            return stringBuilder.toString();

        return null;
    }

    public static byte[] readBytes(InputStream inputStream) {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[1024];
            int len = 0;

            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
        } catch (Exception e) {
            byteBuffer = null;
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (byteBuffer != null)
            return byteBuffer.toByteArray();

        return null;
    }

    public static Object readObject(InputStream inputStream) {
        ObjectInputStream objectInputStream = null;
        Object object = null;

        try {
            objectInputStream = new ObjectInputStream(inputStream);
            object = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }

    public static boolean writeText(OutputStream outputStream, String text) {
        boolean success = true;

        try {
            outputStream.write(text.getBytes());
        } catch (Exception e) {
            success = false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    success = false;
                }
            }
        }

        return success;
    }

    public static boolean writeObject(OutputStream outputStream, Object object) {
        boolean success = true;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            success = false;
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (Exception e) {
                    success = false;
                }
            }
        }

        return success;
    }

    public static InputStream openAssetInputStream(Context context, String fileName) {
        try {
            return context.getAssets().open(fileName);
        } catch (Exception e) {
            return null;
        }
    }

    public static InputStream openRawInputStream(Context context, int rawResId) {
        try {
            return context.getResources().openRawResource(rawResId);
        } catch (Exception e) {
            return null;
        }
    }

    public static FileInputStream openInternalStorageInputStream(Context context, String fileName) {
        try {
            return context.openFileInput(fileName);
        } catch (Exception e) {
            return null;
        }
    }

    public static FileOutputStream openInternalStorageOutputStream(Context context, String fileName) {
        try {
            return context.openFileOutput(fileName, Context.MODE_PRIVATE);
        } catch (Exception e) {
            return null;
        }
    }

    public static FileInputStream openFileInputStream(File file) {
        try {
            return new FileInputStream(file);
        } catch (Exception e) {
            return null;
        }
    }

    public static FileOutputStream openFileOutputStream(File file) {
        try {
            return new FileOutputStream(file);
        } catch (Exception e) {
            return null;
        }
    }
}
