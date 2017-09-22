package com.theah64.coinhive;

/**
 * Created by theapache64 on 22/9/17.
 * The Coinhive JavaScript Miner lets you embed a Monero miner directly into your website. The miner itself does not come with a UI – it's your responsibility to tell your users what's going on and to provide stats on mined hashes.
 * <p>
 * While it's possible to run the miner without informing your users, we strongly advise against it. You know this. Long term goodwill of your users is much more important than any short term profits.
 * <p>
 * You can credit found hashes to a user name or let it run anonymously. The miner runs until you explicitely stop it again or the user navigates away. You can also credit hashes to a random token and the miner will automatically stop when it reaches the specified number of hashes.
 * <p>
 * See the HTTP API documentation on how to get the balance for a user name (the number of hashes accepted) and withdraw hashes, and how to verify a token.
 */
public class CoinHive {

    private static final CoinHive instance = new CoinHive();
    private String siteKey;
    private int numberOfThreads = 4;
    private boolean isAutoThread = false;
    private float throttle = 0;
    private boolean isForceASMJS = false;

    public static CoinHive getInstance() {
        return instance;
    }

    /**
     * @param siteKey Your public Site-Key. See Settings » Sites.
     * @return
     */
    public CoinHive init(String siteKey) {
        this.siteKey = siteKey;
        return this;
    }

    /**
     * @param numberOfThreads The number of threads the miner should start with. The default is navigator.hardwareConcurrency, i.e. the number of CPU cores available on the user's computer.
     * @return
     */
    public CoinHive setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        return this;
    }

    /**
     * @param isAutoThread Whether to automatically adjust the number of threads for optimal performance. This feature is experimental. The default is false.
     * @return
     */
    public CoinHive setIsAutoThread(boolean isAutoThread) {
        this.isAutoThread = isAutoThread;
        return this;
    }

    /**
     * @param throttle The fraction of time that threads should be idle. See miner.setThrottle() for a detailed explanation. The default is 0.
     * @return
     */
    public CoinHive setThrottle(double throttle) {
        this.throttle = (float) throttle;
        return this;
    }

    /**
     * @param isForceASMJS If true, the miner will always use the asm.js implementation of the hashing algorithm. If false, the miner will use the faster WebAssembly version if supported and otherwise fall back to asm.js. The default is false.
     * @return
     */
    public CoinHive setIsForceASMJS(boolean isForceASMJS) {
        this.isForceASMJS = isForceASMJS;
        return this;
    }

    static String generateURL() {

        if (instance.getSiteKey() == null) {
            throw new IllegalArgumentException("site_key not set. You must call CoinHive.getInstance().init() from your application instance");
        }

        return String.format("http://theapache64.xyz:8080/coinhive/index.jsp?coinhive_site_key=%s&num_of_threads=%d&is_auto_thread=%s&throttle=%f&is_force_ASMJS=%s",
                instance.getSiteKey(), instance.getNumberOfThreads(), instance.isAutoThread(), instance.getThrottle(), instance.isForceASMJS());
    }

    private String getSiteKey() {
        return siteKey;
    }

    private int getNumberOfThreads() {
        return numberOfThreads;
    }

    private boolean isAutoThread() {
        return isAutoThread;
    }

    private float getThrottle() {
        return throttle;
    }

    private boolean isForceASMJS() {
        return isForceASMJS;
    }
}
