const {create} = require("hermione");
const assert = require('chai').assert;


describe('create screenplay', async function () {
    beforeEach(async (opts) => {
        opts.host = 'http://localhost:8080/'
        opts.auth_params = {
            director: {
                login: 'khilike',
                password: 'password'
            },
            screenplay_writer: {
                login: 'nosovas',
                password: 'password'
            },
            worker: {
                login: 'ivanetsas',
                password: 'password'
            },
            requester: {
                login: 'okhlobystini',
                password: 'password'
            }
        }
        opts.director_login = 'khilike'
        opts.director_password = 'password'
    });

    async function auth(host, browser, login, password) {
        await logout(browser, host);
        await browser.url(host);

        await browser.$('#username').addValue(login);
        await browser.$('#password').addValue(password);
        await browser.$('.singInButton').click();
        await browser.pause(60);
    }

    async function logout(browser, host) {
        await browser.url(host + 'logout');
        await browser.pause(120);

    }

    it('create new screenplay', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.screenplay_writer.login, auth_params.screenplay_writer.password);

        await browser.$('#menu_container > a:nth-child(6)').click();
        await browser.pause(60);
        await browser.$('#name').addValue('Новый сценарйи');
        await browser.$('#code').addValue('01');
        await browser.$('#content').addValue('Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.');
        await browser.$('#NewTaskContainer\\  > form > div.ApplyButtonContainer > input').click();
        await browser.pause(200);

        await logout(browser, host);
    });

    it('requester agrees with the screenplay', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.requester.login, auth_params.requester.password);

        await browser.$('#menu_container > a:nth-child(5)').click();
        await browser.pause(200);
        await browser.$('#content_container > div:nth-child(2) > div > div > div > div:nth-child(5) > button:nth-child(1)').click();
        await browser.pause(200);

        await logout(browser, host);
    });
});
