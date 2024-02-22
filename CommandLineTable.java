public class CommandLineTable {
    public static void main(String[] args) {
        // Table data
        String[][] data = {
                {"Name", "Age", "City", "Country"},
                {"John Doe", "30", "New York", "USA"},
                {"Alice Smith", "28", "London", "UK"},
                {"Bob Johnson", "35", "Paris", "France"}
        };

        // Print table header
        System.out.printf("| %-15s | %-10s | %-15s | %-10s |\n", data[0][0], data[0][1], data[0][2], data[0][3]);
        // Print table separator
        System.out.println("+-----------------+------------+-----------------+------------+");
        // Print table rows
        for (int i = 1; i < data.length; i++) {
            System.out.printf("| %-15s | %-10s | %-15s | %-10s |\n", data[i][0], data[i][1], data[i][2], data[i][3]);
        }
    }
}
