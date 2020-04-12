package com.app.meucalendario.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.app.meucalendario.model.Dia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Feriados {

    public static List<Dia> feriadosFixos(int ano) {
        List<Dia> listaDeFeriadosFixos = new ArrayList<>();

        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 1, 1), "Ano Novo"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 4, 21), "Tiradentes"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 5, 1), "Dia do Trabalhador"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 6, 12), "Dia dos Namorados"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 9, 7), "Dia da Independência"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 10, 12), "Nossa Senhora Aparecida/Dia das Crianças"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 10, 15), "Dia do Professor"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 11, 2), "Finados"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 11, 15), "Proclamação da República"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 11, 20), "Dia da Consciência Negra"));
        listaDeFeriadosFixos.add(new Dia(LocalDate.of(ano, 12, 25), "Natal"));

        return listaDeFeriadosFixos;
    }

    public static List<Dia> feriadosMoveis(int ano) {
        List<Dia> listaDeFeriadosMoveis = new ArrayList<>();

        //cálculo páscoa
        int X=24;
        int Y=5;
        int a = ano % 19;
        int b= ano % 4;
        int c = ano % 7;
        int d = (19 * a + X) % 30;
        int e = (2 * b + 4 * c + 6 * d + Y) % 7;
        int dia;
        int mes;

        if((d+e) > 9) {
            dia = d+e-9;
            mes = 4;
        }else {
            dia = d+e+22;
            mes = 3;
        }

        LocalDate pascoa = LocalDate.of(ano, mes, dia);
        listaDeFeriadosMoveis.add(new Dia(pascoa, "Páscoa"));
        //sexta feira santa 2 dias antes da páscoa
        listaDeFeriadosMoveis.add(new Dia(pascoa.minusDays(2), "Sexta-Feira Santa"));
        //carnaval 47 dias antes da páscoa
        listaDeFeriadosMoveis.add(new Dia(pascoa.minusDays(47), "Carnaval"));
        //quarta de cinzas
        listaDeFeriadosMoveis.add(new Dia(pascoa.minusDays(46), "Quarta-Feira de Cinzas"));
        //corpus christi 60 após a páscoa
        listaDeFeriadosMoveis.add(new Dia(pascoa.plusDays(60), "Corpus Christi"));

        return listaDeFeriadosMoveis;
    }
}
