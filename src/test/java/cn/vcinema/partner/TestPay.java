/*
 *  Copyright (c) 2017-present, Pumpkin Movie, Inc. All rights reserved.
 *
 *  You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 *  copy, modify, and distribute this software in source code or binary form for use
 *  in connection with the web services and APIs provided by Pumpkin Movie.
 *
 *  As with any software that integrates with the pumpkin movie platform, your use of
 *  this software is subject to the Pumpkin Movie Developer Principles and Policies
 *  [http://developers.vcinema.com/policy/]. This copyright notice shall be
 *  included in all copies or substantial portions of the software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cn.vcinema.partner;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Xulin Zhuang
 * Date: 29/1/2018
 * Time: 12:50 PM
 */
public class TestPay {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getRedeemCode() throws Exception {
        String signatureNonce = Random.getRandom(10,Random.TYPE.LETTER_CAPITAL_NUMBER);
        long timestamp = System.currentTimeMillis();

        Map params = new HashMap<>();
        params.put("pid",PartnerInfo.pid);
        params.put("code_type",PartnerInfo.codeType);
        params.put("timestamp",timestamp);

        List<NameValuePair> parameter = new ArrayList<>();
        parameter.add(new BasicNameValuePair("pid", PartnerInfo.pid));
        parameter.add(new BasicNameValuePair("code_type", PartnerInfo.codeType));
        parameter.add(new BasicNameValuePair("timestamp", timestamp+""));

        parameter.add(new BasicNameValuePair("sign", PartnersApiSignature.partnersApiSignature(PartnerInfo.httpMethod,PartnerInfo.action,PartnerInfo.format,PartnerInfo.pid,signatureNonce,PartnerInfo.accessSecret,timestamp,params)));

        System.out.println(HttpClientUtil.doPost("http://127.0.0.1:3505/pay/redeem_code",signatureNonce,parameter));

    }
}
