package br.com.senaijandira.viacep

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //buscar cep
        btn_buscar.setOnClickListener {


            val cep = txt_cep.text.toString()

            //executar tarefas em segundo plano
            doAsync {

                //Lendo a internet e retornando o resultado em json
                val resultado = URL("http://viacep.com.br/ws/$cep/json/").readText()

                //transformando json pra objeto
                val retornoJson = JSONObject(resultado)

                //pegando cada variavel
                val logradouro = retornoJson.getString("logradouro")
                val cidade = retornoJson.getString("localidade")
                val bairro = retornoJson.getString("bairro")

                //executa na Thread principal
                uiThread {
                    txt_logradouro.text = logradouro
                    txt_cidade.text = cidade
                    txt_bairro.text = bairro
                }
            }




        }

    }
}
