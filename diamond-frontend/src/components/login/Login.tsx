import { FC } from "react"
import { Button } from "../ui/button"
import { Card, CardHeader, CardTitle, CardDescription, CardContent, CardFooter } from "../ui/card"

const LoginCard: FC = () => {
    return(
    <div className='flex items-center justify-center'>
    {/* <Button variant={'newVariant'}>Click me</Button> */}
    <Card>
      <CardHeader>
        <CardTitle>Continue with</CardTitle>
        <CardDescription>Choose a method to continue</CardDescription>
      </CardHeader>
      <CardContent>
        <Button>Sign in with Google</Button>
        <Button>Sign in with GitHub</Button>
      </CardContent>
      <CardFooter>
        <p>By continuing, you agree to our terms and conditions.</p>
      </CardFooter>
    </Card>
  </div>)
}

export default LoginCard;