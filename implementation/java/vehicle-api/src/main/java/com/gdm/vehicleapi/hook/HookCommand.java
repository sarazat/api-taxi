package com.gdm.vehicleapi.hook;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbm.vehicle.pojos.Hook;
import com.gbm.vehicle.pojos.Position;
import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.position.PositionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

@Service
public class HookCommand {

  @Autowired
  private HookService hookService;

  @Autowired
  private Function<PositionEntity, Position> mapPositionEntityToPojo;

  @Autowired
  ObjectMapper mapper;


  public void triggerHook(PositionEntity positionEntity) throws IOException {


    List<HookEntity> hooks = hookService.findAllPositionsByVehicle(positionEntity.getVehicle());

    System.out.println("------> No. of subscribers " + hooks.size());
    for(HookEntity hook:hooks){

      System.out.println("-----> Going to post " + hook.getPayLoadUrl());
      URL url = new URL(hook.getPayLoadUrl());
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");

    
      String input = mapper.writeValueAsString(mapPositionEntityToPojo.apply(positionEntity));

      System.out.println("-----> Payload " + input);

      OutputStream os = conn.getOutputStream();
      os.write(input.getBytes());
      os.flush();



      hook.setLatestStatusCode(conn.getResponseCode());

      System.out.println("----->> Response code" + hook.getLatestStatusCode());

      hookService.save(hook);

    }



  }
}
