// import { FC } from "react";

// const Lander: FC = () => {
    
// }

// export default Lander;

import { useEffect, useMemo, useState } from "react";
import { motion } from "framer-motion";
import { MoveRight } from "lucide-react";
import { Button } from "@/components/ui/button";
import { FaGithub } from "react-icons/fa";
import { Link } from "react-router-dom";

function LandingPage() {
  const [titleNumber, setTitleNumber] = useState(0);
  const titles = useMemo(
    () => ["ultra-fast", "low-fee", "cross-border", "secure"],
    []
  );

  useEffect(() => {
    const timeoutId = setTimeout(() => {
      if (titleNumber === titles.length - 1) {
        setTitleNumber(0);
      } else {
        setTitleNumber(titleNumber + 1);
      }
    }, 2000);
    return () => clearTimeout(timeoutId);
  }, [titleNumber, titles]);

  return (
    <div className="w-full">
      <div className="container mx-auto">
        <div className="md:pt-5 flex justify-end items-center gap-5">
            <Link to="/login">
                <Button variant="outline" className="gap-4">
                Dashboard <MoveRight className="w-4 h-4" />
                </Button>
            </Link>
            <a href="https://www.github.com/ayanmali/Diamond"><FaGithub className="size-8"/></a>
        </div>
        <div className="flex gap-8 py-20 lg:py-40 items-center justify-center flex-col">
          <div className="flex gap-4 flex-col">
            <h1 className="text-5xl md:text-7xl max-w-3xl tracking-tighter text-center font-regular">
              <span className="text-spektr-cyan-50">Stablecoin infrastructure for</span>
              <span className="relative flex w-full justify-center overflow-hidden text-center md:pb-2 md:pt-2">
                &nbsp;
                {titles.map((title, index) => (
                  <motion.span
                    key={index}
                    className="absolute font-semibold"
                    initial={{ opacity: 0, y: "-100" }}
                    transition={{ type: "spring", stiffness: 50 }}
                    animate={
                      titleNumber === index
                        ? {
                            y: 0,
                            opacity: 1,
                          }
                        : {
                            y: titleNumber > index ? -150 : 150,
                            opacity: 0,
                          }
                    }
                  >
                    {title}
                  </motion.span>
                ))}
              </span>
              <span className="text-spektr-cyan-50">payments.</span>
              
            </h1>

            <p className="text-lg md:text-xl leading-relaxed tracking-tight text-muted-foreground max-w-2xl text-center md:pt-2">
              Traditional payment infrastructure comes with too much hassle,
              between high fees, long delays, and frozen accounts.
              Our goal is to streamline payment infrastructure by leveraging the power of
              stablecoins to bring financial control back into the hands of business owners, charities, and creators.
            </p>
          </div>
          <div className="flex flex-row gap-3">
            {/* <Button size="lg" className="gap-4" variant="outline">
              View the repository <PhoneCall className="w-4 h-4" />
            </Button> */}
            <Link to="/login">
                <Button variant={"default_hover"} size="lg" className="gap-4">
                Get started <MoveRight className="w-4 h-4" />
                </Button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export { LandingPage };