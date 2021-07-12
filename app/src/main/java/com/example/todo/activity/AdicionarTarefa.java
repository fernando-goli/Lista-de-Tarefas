package com.example.todo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.todo.R;
import com.example.todo.helper.TarefaDAO;
import com.example.todo.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefa extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);
        //recuperar tarefa, caso edicao
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelect");
        //configurar tarefa na caixa de texto
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                //executa ação para o item salvar
                if(tarefaAtual != null){//edicao
                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()){

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        if(tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Tarefa salvo com sucesso", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();

                        }

                    }

                } else {//salvar
                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if (tarefaDAO.salvar(tarefa) ) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Tarefa salvo com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
