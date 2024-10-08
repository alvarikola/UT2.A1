package com.example.ut2a1

import android.os.Bundle
//https://developer.android.com/reference/android/os/Bundle
import androidx.activity.ComponentActivity
//https://developer.android.com/reference/androidx/activity/ComponentActivity
import android.os.Environment
//https://developer.android.com/reference/android/os/Environment
import android.util.Log
//https://developer.android.com/reference/android/util/Log
import java.io.BufferedReader
//https://developer.android.com/reference/androidx/activity/ComponentActivity
import java.io.File
//https://developer.android.com/reference/java/io/File
import java.io.FileOutputStream
//https://developer.android.com/reference/java/io/FileOutputStream
import java.io.FileReader
//https://developer.android.com/reference/java/io/FileReader
import java.io.OutputStreamWriter
//https://developer.android.com/reference/java/io/OutputStreamWriter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombreArchivo = "ejemplo.txt"
        val texto = "Hola me llamo Alvaro"
        Escribir(texto, nombreArchivo)
        Leer(nombreArchivo)


    }


    fun Escribir(texto: String, nombreArchivo: String) {
        val estadoAlmacenamiento = Environment.getExternalStorageState()

        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val flujoSalida = FileOutputStream(archivo, true)
                val writer = OutputStreamWriter(flujoSalida)
                writer.append(texto)
                writer.close()

                Log.i("DAM2", "Añadido en $directorio $nombreArchivo")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("DAM2", "Error al guardar")
            }
        }else {
            Log.i("DAM2", "No se pudo acceder al almacenamiento externo")
        }
    }

    fun Leer(nombreArchivo: String) {
        val estadoAlmacenamiento = Environment.getExternalStorageState()
        if (estadoAlmacenamiento == Environment.MEDIA_MOUNTED) {
            val directorio = getFilesDir()
            val archivo = File(directorio, nombreArchivo)
            try {
                val flujoEntrada = FileReader(archivo)
                val leer = BufferedReader(flujoEntrada)
                val contenidoArchivo = leer.readLines()
                leer.close()

                Log.i("DAM2", "Contenido del archivo: $contenidoArchivo")
                flujoEntrada.close()
                // File(directorio, nombreArchivo).delete() esto es para borrar y que no se acumulen los datos
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("DAM2", "Error al leer")
            }
        }else {
            Log.i("DAM2", "No se pudo acceder al almacenamiento externo")
        }
    }


}

