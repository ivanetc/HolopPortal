const assert = require('chai').assert;



describe('header and menu', async function() {
    beforeEach(async (opts) => {
        opts.host = 'http://localhost:8080/'
        opts.auth_params = {
            director: {
                login: 'khilike',
                password: 'password'
            },
            worker: {
                login: 'ivanetsas',
                password: 'password'
            },
            screenplay_writer: {
                login: 'nosovas',
                password: 'password'
            }
        }
        opts.director_login = 'khilike'
        opts.director_password = 'password'
    });

    async function auth(browser, login, password) {
        await browser.$('#username').addValue(login);
        await browser.$('#password').addValue(password);
        await browser.$('.singInButton').click();
        await browser.pause(60);
    }

    async function logout(browser, host) {
        await browser.url(host + 'logout');
    }

    it('check director username and role labels', async ({ browser, host, auth_params }) => {
        await browser.url(host);
        await auth(browser, auth_params.director.login, auth_params.director.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Egor Khilik')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Режиссер')

        await logout(browser, host);
    });

    it('check worker username and role labels', async ({ browser, host, auth_params }) => {
        await browser.url(host);
        await auth(browser, auth_params.worker.login, auth_params.worker.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Alexander Ivanets')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Сотрудник')

        await logout(browser, host);
    });
});