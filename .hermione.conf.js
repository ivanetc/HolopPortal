module.exports = {
    gridUrl: 'http://localhost:4444/wd/hub',

    sets: {
        desktop: {
            files: 'tests/desktop'
        }
    },

    browsers: {
        chrome: {
            automationProtocol: 'webdriver', // default value
            desiredCapabilities: {
                browserName: 'chrome',
            },
            headers: {
                Cookie: 'Idea-16968d96=da9b627e-2659-42ed-93f3-a3d142095d09; _ym_uid=167459148074111357; _ym_d=1674591480; JSESSIONID=CD8E55DDD1D2034EE9F3F9CE74CE7489',
            }
        }
    },


    system: {
        debug: true
    }
};