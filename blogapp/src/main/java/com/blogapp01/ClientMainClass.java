package com.blogapp01;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMainClass {
    public static void main(String[] args) {
        Client c1 = new Client();
        c1.setId(1);
        c1.setName("Sahil");

        Client c2 = new Client();
        c2.setId(2);
        c2.setName("Niraj");

        List<Client> emp = Arrays.asList(c1, c2);
        List<ClientDto> empdto = emp.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        System.out.println(empdto);
    }

     static ClientDto mapToDto(Client c){
        ClientDto dto = new ClientDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        return dto;
    }
}
