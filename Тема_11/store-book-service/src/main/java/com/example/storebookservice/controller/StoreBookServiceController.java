package com.example.storebookservice.controller;

import com.example.storebookservice.model.RemotePropertyField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreBookServiceController {
   private RemotePropertyField field;

   public StoreBookServiceController(RemotePropertyField field) {
       this.field = field;
   }

   @GetMapping(value = "/getRemoteField")
   public String getRemotePropertyField() {
       return "Data: " + field.getField();
   }
}
