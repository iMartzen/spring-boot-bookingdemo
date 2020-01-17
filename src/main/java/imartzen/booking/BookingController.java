package imartzen.booking;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private List<HotelBooking> bookings;

    public BookingController(){
        bookings = new ArrayList<>();

        bookings.add(new HotelBooking("Hoteldebotel", 90, 3));
        bookings.add(new HotelBooking("Kakelbont", 100, 2));
        bookings.add(new HotelBooking("Pillow", 230, 1));

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<HotelBooking> getAll(){
        return bookings;
    }

    @RequestMapping(value = "/affordable/{price}", method = RequestMethod.GET)
    public List<HotelBooking> getAffordable(@PathVariable double price){
        return bookings.stream().filter(x -> x.getPricePerNight() <= price)
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public List<HotelBooking> create(@RequestBody HotelBooking hotelBooking){
        bookings.add(hotelBooking);
        return bookings;
    }

}
