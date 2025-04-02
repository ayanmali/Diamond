import { FC } from "react"
import { Button } from "../ui/button"
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from "../ui/card"
import { FaGoogle, FaGithub } from "react-icons/fa";
import { Link } from "react-router-dom";

const LoginCard: FC = () => {
    return(
    <div className="flex items-center justify-center h-screen md:pb-10">
        <div className='items-center justify-center'>
        {/* <Button variant={'newVariant'}>Click me</Button> */}
            <Card className="items-center w-xl stroke-accent-foreground">
            <CardHeader className="text-center w-full">
                <CardTitle className="text-2xl">Continue with</CardTitle>
                <CardDescription className="text-lg">Choose a method to continue</CardDescription>
            </CardHeader>
            <CardContent className="size-6 w-full flex justify-center">
                <Link to="/oauth2/authorization/google">
                    <Button variant={"ghost"} size={"login"}><FaGoogle />Sign in with Google </Button>
                </Link>
            </CardContent>

            <CardContent className="size-6 w-full flex justify-center">
                <Link to="/oauth2/authorization/github">
                    <Button variant={"ghost"} size={"login"}><FaGithub />Sign in with GitHub</Button>
                </Link>
            </CardContent>
            <CardFooter className="justify-center md:pt-3">
                <CardDescription className="text-center">By continuing, you agree to our terms and conditions.</CardDescription>
            </CardFooter>
            </Card>
        </div>
  </div>)
}

export default LoginCard;