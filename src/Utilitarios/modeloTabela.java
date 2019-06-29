/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lucas
 */
public class modeloTabela extends AbstractTableModel {
    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public modeloTabela(ArrayList linha, String[] coluna){
        setLinhas(linha);
        setColunas(coluna);
        
    }
    
    public ArrayList getLinhas(){
        return linhas;
    }
    
    public void setLinhas(ArrayList dados){
        linhas = dados;
    }
    
    public String[] getColunas(){
        return colunas;
    }
    
    public void setColunas(String[] nomes){
        colunas = nomes;
    }
    
    public int getColumnCount(){
        return colunas.length;
    }
    
    public int getRowCount(){
        return linhas.size();
    }
    
    
    
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    
    public Object getValueAt(int numLinhas, int numColunas){
        Object[] linha = (Object[])getLinhas().get(numLinhas);
        
        return linha[numColunas];
        
    }
    
    public void setRowCounts(){
        
    }    
    public void remove(int indexRow) {
        if(indexRow< linhas.size()){     
            linhas.remove(indexRow);
            fireTableRowsDeleted(indexRow, indexRow);
        }
    }

//    public void remove(T element) {
//        int index = linhas.indexOf(element);
//        linhas.remove(element);
//        fireTableRowsDeleted(index, index);
//    }
    
}
