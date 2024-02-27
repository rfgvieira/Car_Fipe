package com.rfgvieira.Car_Fipe.main;

import com.rfgvieira.Car_Fipe.model.Data;
import com.rfgvieira.Car_Fipe.model.Model;
import com.rfgvieira.Car_Fipe.model.Vehicle;
import com.rfgvieira.Car_Fipe.model.VehicleData;
import com.rfgvieira.Car_Fipe.service.CastData;
import com.rfgvieira.Car_Fipe.service.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final String CORE_URL = "https://parallelum.com.br/fipe/api/v1/";
    private final Scanner sc = new Scanner(System.in);
    private Service service = new Service();
    private CastData castData = new CastData();

    private String url;

    public void menu() {
        System.out.println("Escolha o tipo de veículo desejado");

        System.out.println("1 - Carros");
        System.out.println("2 - Caminhões");
        System.out.println("3 - Motos");
        System.out.println("4 - Sair do Sistema\n");


        System.out.print("Digite a opção desejada: ");
        var typed = sc.nextLine();

        if (typed.isEmpty()) {
            System.out.println("Opção Inválida!");
            menu();
            return;
        }

        var option = Integer.parseInt(typed);


        var tipo = "";
        switch (option) {
            case 1:
                tipo = "carros";
                break;
            case 2:
                tipo = "caminhoes";
                break;
            case 3:
                tipo = "motos";
                break;
            case 4:
                tipo = "sair";
                break;
            default: {
                System.out.println("Opção não encontrada!");
                menu();
                return;
            }
        }


        if (tipo.equals("sair"))
            return;

        getBrands(tipo);

    }

    private void getBrands(String tipo) {
        url = CORE_URL + tipo + "/marcas";
        var json = service.getDataFromApi(url);
        if (json.isPresent()) {
            var brands = castData.getList(json.get(), Data.class);
            brands.stream().sorted(Comparator.comparing(Data::code)).forEach(System.out::println);

            var isError = true;

            while (isError) {
                System.out.print("\nDigite o código da marca desejada: ");
                var code = sc.nextLine();
                url = url + "/" + code + "/modelos";
                json = service.getDataFromApi(url);
                if (json.isPresent()) {
                    isError = false;
                    getModels(json.get());
                } else {
                    System.out.println("Código Inválido");
                }

            }
        }


    }

    private void getModels(String json) {
        var models = castData.castData(json, Model.class);
        models.modelList().forEach(System.out::println);

        var isErrorFilter = true;
        while (isErrorFilter) {
            System.out.print("\nDigite um trecho do nome do modelo desejado: ");
            var modelName = sc.nextLine();

            var modelFiltered = models.modelList().stream().filter(m -> m.name().toLowerCase().contains(modelName.toLowerCase())).toList();
            if (!modelFiltered.isEmpty()) {
                isErrorFilter = false;
                modelFiltered.forEach(System.out::println);
            } else {
                System.out.println("Nenhum modelo encontrado!\n");
            }
        }

        var isErrorModel = true;

        while (isErrorModel) {
            System.out.println("\nDigite o código do modelo desejado: ");
            var modelCode = sc.nextLine();

            url = url + "/" + modelCode + "/anos";
            var jsonModel = service.getDataFromApi(url);
            if (jsonModel.isPresent()) {
                isErrorModel = false;
                getValues(jsonModel.get());
            } else {
                System.out.println("Código Inválido!\n");
            }


        }


    }

    private void getValues(String json) {

        var years = castData.getList(json, Data.class);


        years.forEach(y -> {
                    Vehicle vehicle = getVehicle(y.code());
                    System.out.println(vehicle);
                }
        );


    }

    private Vehicle getVehicle(String code) {
        var urlVehicle = url + "/" + code;
        var json = service.getDataFromApi(urlVehicle);
        if (json.isPresent()) {
            VehicleData vehicleData = castData.castData(json.get(), VehicleData.class);

            return new Vehicle(
                    vehicleData.brand(),
                    vehicleData.year().toString(),
                    vehicleData.model(),
                    vehicleData.fuel(),
                    vehicleData.value());
        }

        return null;

    }
}
