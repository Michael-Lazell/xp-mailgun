var portal = require('/lib/xp/portal');
var UTIL = require('/lib/enonic/util/util');

exports.mailgunSend = function(path, to, from, subject, text, debug) {

    var siteConfig = portal.getSiteConfig();

    var apiKey = siteConfig.mailgunApiKey;
    var resourceUrl = siteConfig.mailgunResourceUrl;

    var bean = __.newBean("com.enonic.mailgun.MailgunEmail");

    var result = bean.sendSimpleMessage( apiKey, resourceUrl, path, to, from, subject, text, debug );

    return JSON.parse(result);
    //return result;
};