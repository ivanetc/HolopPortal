const {create} = require("hermione");
const assert = require('chai').assert;


describe('create user', async function () {
    beforeEach(async (opts) => {
        opts.host = 'http://localhost:8080/'
        opts.auth_params = {
            new_user: {
                login: 'ivanovi',
                password: 'password'
            }
        }
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

    it('create new user', async ({browser, host, auth_params}) => {
        await logout(browser, host);
        await browser.url(host);
        await browser.$('#main_div > div > div > form > fieldset > div:nth-child(4) > p > a').click();
        await browser.$('#first_name').addValue('Иван');
        await browser.$('#last_name').addValue('Иванов');
        await browser.$('#login').addValue(auth_params.new_user.login);
        await browser.$('#password').addValue(auth_params.new_user.password);
        await browser.pause(120);
        await browser.$('#reg-form > div:nth-child(4) > div > button').click()

        await logout(browser, host);
    });

    it('log in new user', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.new_user.login, auth_params.new_user.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Иван Иванов')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Сотрудник')

        const mainMenuButton = await browser.$("#menu_container > a:nth-child(2)").getText();
        assert.equal(mainMenuButton, "Главная страницa");
        const tasksButton = await browser.$("#menu_container > a:nth-child(3)").getText();
        assert.equal(tasksButton, "Текущие задачи");
        const listRolesButton = await browser.$("#menu_container > a:nth-child(4)").getText();
        assert.equal(listRolesButton, "Список ролей");
        const listScreenplaysButton = await browser.$("#menu_container > a:nth-child(5)").getText();
        assert.equal(listScreenplaysButton, "Список сценариев");

        await logout(browser, host);
    });
});
