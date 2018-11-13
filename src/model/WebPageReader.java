package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebPageReader {

    public static void main(String[] args) throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}

//copied from 10.Design Patterns and the Web (https://edge.edx.org/courses/course-v1:UBC+CPSC210+2018W1/courseware/a4d49b3ef5fa4fe2bd9496e76d72dc48/e2887456a15a48dbb040ecdac313168f/10?activate_block_id=block-v1%3AUBC%2BCPSC210%2B2018W1%2Btype%40vertical%2Bblock%4038dc1fe6e45a420ea1936b0165c6e58d)