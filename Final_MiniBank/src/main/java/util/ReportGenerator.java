package util;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public final class ReportGenerator {
    private ReportGenerator() { }
    public static String generate(Path logsFolder,Path reportFile) throws IOException {
        long deposits=0,withdrawals=0; StringBuilder details=new StringBuilder("MINIBANK END-OF-DAY REPORT\n");
        Files.createDirectories(logsFolder);
        try(DirectoryStream<Path> stream=Files.newDirectoryStream(logsFolder)) {
            for(Path file:stream) if(Files.isRegularFile(file)) {
                BasicFileAttributes attrs=Files.readAttributes(file,BasicFileAttributes.class);
                details.append("File: ").append(file.getFileName()).append(" | Size: ").append(attrs.size()).append(" bytes | Modified: ").append(attrs.lastModifiedTime()).append('\n');
                try(BufferedReader reader=Files.newBufferedReader(file)) { String line; while((line=reader.readLine())!=null) {
                    String[] p=line.split("\\|"); if(p.length>=2) try {long amount=Long.parseLong(p[p.length-1].trim()); String joined=String.join("|",p); if(joined.contains("|DEPOSIT|")) deposits+=amount; if(joined.contains("|WITHDRAW|")) withdrawals+=amount;}catch(NumberFormatException ignored){}
                }}
            }
        }
        details.append("Total deposits: Rs. ").append(deposits).append('\n').append("Total withdrawals: Rs. ").append(withdrawals).append('\n');
        Files.createDirectories(reportFile.toAbsolutePath().getParent()); Files.writeString(reportFile,details.toString()); return details.toString();
    }
}
