package com.gdm.vehicleapi.position;

import com.gbm.vehicle.pojos.Position;
import com.gdm.vehicleapi.GenericController;
import com.gdm.vehicleapi.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class PositionController extends GenericController<Position, PositionService> {

  @Autowired
  public PositionController(@Value("${app.max.rows}") int maxRows, PositionService positionService) {
    super(maxRows, positionService);
  }


  @PostMapping("/vehicle/{uuid}/location")
  public Position save(@PathVariable(name = "uuid") final String vehicleUuidStr, @Valid @RequestBody Position position) {
    return s.save(position, UUID.fromString(vehicleUuidStr));
  }


  @GetMapping("/vehicle/{uuid}/location")
  public ResponseEntity<List<Position>> findAll(@PathVariable(name = "uuid") final String vehicleUuidStr,
                                                @RequestParam(name = "page", defaultValue = "0") final Integer page) {

    PageRequest pageRequest = PageRequest.of(page, maxRows);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pageRequest);
    Page<Position> pPage = s.findAllPositionsByVehicleId(UUID.fromString(vehicleUuidStr), pageRequest);
    return new ResponseEntity<>(pPage.getContent(), headers, HttpStatus.OK);

  }


}
