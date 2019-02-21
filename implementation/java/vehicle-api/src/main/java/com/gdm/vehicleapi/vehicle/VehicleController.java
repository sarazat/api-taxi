package com.gdm.vehicleapi.vehicle;

import com.gbm.vehicle.pojos.Position;
import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class VehicleController extends GenericController<Vehicle,VehicleService> {

  @Autowired
  public VehicleController(@Value("${app.max.rows}") int  maxRows, VehicleService vehicleService) {
    super(maxRows, vehicleService);
  }

  @Override
  @PostMapping("/vehicle")
  public Vehicle save(@Valid @RequestBody Vehicle vehicle) {
    return super.save(vehicle);
  }

  @GetMapping("/vehicle/{uuid}")
  public Vehicle findById(@PathVariable(name = "uuid") String uuid){
    return super.findById(uuid);
  }

  @Override
  @GetMapping("/vehicles")
  public ResponseEntity<List<Vehicle>> findAll(@RequestParam(name = "page", defaultValue = "0") final  Integer page) {
    return super.findAll(page);
  }


}
