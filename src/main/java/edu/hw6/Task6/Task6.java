package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int BEGIN_PORT = 0;
    private static final int END_PORT = 49151;
    private static final String OUT_FORMAT = "%-8s\t%-5s\t%s";
    private static final Map<Integer, String> PORT_NAMES = Map.of(
        108, "SNAGAS (SNA Gateway Access Server)",
        123, "NTP (Network Time Protocol)",
        135, "EPMAP (DCE Endpoint Mapper)",
        3305, "OFTP (Odette File Transfer Protocol)",
        4899, "RAdmin Port",
        5353, "MDNS (Multicast DNS)",
        5355, "LLMNR (Link-Local Multicast Name Resolution)",
        6969, "BitTorrent tracker"
    );

    private Task6() {
    }

    public static void portScanner() {
        LOGGER.info(OUT_FORMAT.formatted("Протокол", "Порт", "Сервис"));

        for (int port = BEGIN_PORT; port <= END_PORT; ++port) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                // If the port is already allocated, then catch IOException
            } catch (IOException exception) {
                LOGGER.info(OUT_FORMAT.formatted("TCP", port,
                    PORT_NAMES.get(port) == null ? "" : PORT_NAMES.get(port)));
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
                // If the port is already allocated, then catch IOException
            } catch (IOException exception) {
                LOGGER.info(OUT_FORMAT.formatted("UDP", port,
                    PORT_NAMES.get(port) == null ? "" : PORT_NAMES.get(port)));
            }
        }
    }
}
