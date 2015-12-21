var mailgun = require('mail-gun');
var portal = require('/lib/xp/portal');
var UTIL = require('/lib/enonic/util/util');

exports.post = handlePost;
exports.get = handleGet;

function handlePost(req) {

    var params = req.params;

    var path = '/messages';
    var to = params.to;
    var from =  params.from || 'Enonic Admin <admin@example.com>'
    var subject = params.subject || 'Test email from mailgun app';
    var text = params.text || 'Testing email sent from the mailgun app\n on my development machine.';
    var debug = req.params.debug;

    var result = mailgun.mailgunSend(path, to, from, subject, text, debug);

    return {
        contentType: 'application/json',
        body: result
    }
}

function handleGet(req) {

    var params = req.params;

    var path = '/messages';
    var to = params.to;
    var from =  params.from || 'Enonic Admin <admin@example.com>'
    var subject = params.subject || 'Test email from mailgun app';
    var text = params.text || 'Testing email sent from the mailgun app\n on my development machine.';
    var debug = req.params.debug;

    var result = mailgun.mailgunSend(path, to, from, subject, text, debug);

    return {
        contentType: 'application/json',
        body: result
    }
}